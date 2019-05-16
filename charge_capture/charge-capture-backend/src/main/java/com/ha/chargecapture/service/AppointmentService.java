package com.ha.chargecapture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ha.chargecapture.dao.ChargeCaptureDAO;
import com.ha.chargecapture.dto.AppointmentDetailDTO;
import com.ha.chargecapture.dto.AppointmentRequestDTO;
import com.ha.chargecapture.dto.PatientAppointmentDetail;
import com.ha.chargecapture.entity.PatientDetail;


@Service
public class AppointmentService {
	
	@Autowired
	AuthService authservice;
	
	@Autowired
	ChargeCaptureDAO chargeCaptureDAO;
	
	
	@Value("${appointments.url}")
	private String appointmentUrl;
	
	private static final Logger LOGGER = ESAPI.getLogger(AppointmentService.class);
	
	public List<PatientAppointmentDetail> getAppointments(AppointmentRequestDTO appointmentDto){
		
		List<PatientAppointmentDetail> patientAppointmentDetailList = new ArrayList<>();
		
		String postResp = null;
		
		
		StringBuilder url = new StringBuilder(appointmentUrl);
		
		if(appointmentDto.getStartDate()!=null)
			url.append("startDate="+appointmentDto.getStartDate()+"&");
		
		if(appointmentDto.getEndDate()!=null)
			url.append("endDate="+appointmentDto.getEndDate()+"&");
			
		if(appointmentDto.getStatus()!=null)
			url.append("status="+appointmentDto.getStatus()+"&");
			
		if(appointmentDto.getPractitioner()!=null)
			url.append("practitioner="+appointmentDto.getPractitioner()+"&");
			
		if(appointmentDto.getLocation()!=null)
			url.append("location="+appointmentDto.getLocation());
		
		
		
		
		PostMethod post = new PostMethod(url.toString());
		
		List<AppointmentDetailDTO>  appointmentList= new ArrayList<>();
		
		try {
		
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		
		
		String authArgs = gson.toJson(authservice);
		
		
		
		post.setRequestHeader("authArgs", authArgs);
		post.setRequestHeader("Content-Type", "application/json");
		
		
		
		HttpClient httpclient = new HttpClient();
		
		int statusCode = httpclient.executeMethod(post);
			
	    LOGGER.debug(Logger.EVENT_SUCCESS, "appointments call : " + statusCode);
	    
	    postResp= post.getResponseBodyAsString();
	    
	    appointmentList = new Gson().fromJson(post.getResponseBodyAsString(), new TypeToken<List<AppointmentDetailDTO>>() {
		}.getType());
	    
	    List<String> patientIdList = getPatientIdList(appointmentList);
	    
	    List<PatientDetail> patientDetailList = chargeCaptureDAO.getPatientDetailListById(patientIdList);
	    
	    for(AppointmentDetailDTO appointmentDetailDTO:appointmentList){
	    	
	    	Optional<PatientDetail> matchingObject = patientDetailList.stream().
	    		    filter(p -> p.getPatientId().equals(appointmentDetailDTO.getPatientId())).
	    		    findFirst();
	    	PatientAppointmentDetail patientAppointmentDetail = new PatientAppointmentDetail();
	    	patientAppointmentDetail.setAppointmentDetailDTO(appointmentDetailDTO);
	    	patientAppointmentDetail.setPatientDetail(matchingObject.orElse(null));
	    	patientAppointmentDetailList.add(patientAppointmentDetail);
	    }
	    
	   
		}
		 catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {
			post.releaseConnection();
		}
		
		return patientAppointmentDetailList;
	}
	
	private List<String> getPatientIdList(List<AppointmentDetailDTO> appointmentList){
		
		return appointmentList.stream()
	              .map(AppointmentDetailDTO::getPatientId)
	              .collect(Collectors.toList());
		
		
	}

}


