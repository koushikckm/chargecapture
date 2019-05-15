package com.ha.chargecapture.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.ha.chargecapture.dto.AppointListDTO;
import com.ha.chargecapture.dto.AppointmentDTO;


@Service
public class AppointmentService {
	
	@Autowired
	AuthService authservice;
	
	
	@Value("${appointments.url}")
	private String appointmentUrl;
	
	private static final Logger LOGGER = ESAPI.getLogger(AppointmentService.class);
	
	public List<AppointListDTO> getAppointments(AppointmentDTO appointmentDto){
		
		String postResp = null;
		
		
		
		PostMethod post = new PostMethod(appointmentUrl);
		
		List<AppointListDTO>  appointmentList= new ArrayList<>();
		
		try {
		Gson gson = new Gson();
		
		authservice.setTokenEndpoint("https://172.16.60.10:9443/gedemoAPIServer/oauth2/token?state=defaultState&scope=user/*.*&grant_type=password&");
		
		authservice.setGECPSDatabaseConnection("Server=172.16.60.10\\\\MSSQLSERVER2014;Database=GEDemo;User Id=sa;password=Server@2014;");
		
		authservice.setCADatabaseConnection("data source=DEVSRV2012;initial catalog=CAIntergy64;user id=sa;pwd=Health@1234;");
		
		String authArgs = gson.toJson(authservice);
		
		
		
		post.setRequestHeader("authArgs", authArgs);
		post.setRequestHeader("Content-Type", "application/json");
		if(appointmentDto.getStartDate()!=null)
			post.setParameter("startDate", appointmentDto.getStartDate());
		if(appointmentDto.getEndDate()!=null)
			post.setParameter("endDate", appointmentDto.getEndDate());
		if(appointmentDto.getStatus()!=null)
			post.setParameter("status", appointmentDto.getStatus());
		if(appointmentDto.getPractitioner()!=null)
			post.setParameter("practitioner", appointmentDto.getPractitioner());
		if(appointmentDto.getPractitioner()!=null)
			post.setParameter("location", appointmentDto.getLocation());
		
		
		HttpClient httpclient = new HttpClient();
		
		int statusCode = httpclient.executeMethod(post);
			
	    LOGGER.debug(Logger.EVENT_SUCCESS, "appointments call : " + statusCode);
	    
	    postResp= post.getResponseBodyAsString();
	    
	    appointmentList = new Gson().fromJson(post.getResponseBodyAsString(), new TypeToken<List<AppointListDTO>>() {
		}.getType());
	    
	    
	   
		}
		 catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {
			post.releaseConnection();
		}
		
		return appointmentList;
	}

}
