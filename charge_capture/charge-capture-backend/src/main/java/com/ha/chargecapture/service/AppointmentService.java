package com.ha.chargecapture.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import com.ha.chargecapture.dto.PatientAppointmentDetail;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.exception.ChargeCaptureServiceException;

@Service
public class AppointmentService {

	@Autowired
	AuthService authservice;

	@Autowired
	ChargeCaptureDAO chargeCaptureDAO;

	@Value("${appointments.url}")
	private String appointmentUrl;

	private static final Logger LOGGER = ESAPI.getLogger(AppointmentService.class);

	public List<PatientAppointmentDetail> getAppointments(String startDate, String endDate, String location,
			String status, String practitioner) {

		List<PatientAppointmentDetail> patientAppointmentDetailList = new ArrayList<>();

		String postResp = null;

		StringBuilder url = new StringBuilder(appointmentUrl);

		if (null != startDate)
			url.append("startDate=" + startDate + "&");

		if (null != endDate)
			url.append("endDate=" + endDate + "&");

		if (null != location)
			url.append("location=" + location);

		if (null != status)
			url.append("status=" + status + "&");

		if (null != practitioner)
			url.append("practitioner=" + practitioner + "&");

		PostMethod post = new PostMethod(url.toString());

		List<AppointmentDetailDTO> appointmentList = new ArrayList<>();

		try {

			Gson gson = new GsonBuilder().disableHtmlEscaping().create();

			String authArgs = gson.toJson(authservice);

			post.setRequestHeader("authArgs", authArgs);
			post.setRequestHeader("Content-Type", "application/json");

			HttpClient httpclient = new HttpClient();

			int statusCode = httpclient.executeMethod(post);

			LOGGER.debug(Logger.EVENT_SUCCESS, "appointments call : " + statusCode);

			postResp = post.getResponseBodyAsString();

			appointmentList = new Gson().fromJson(post.getResponseBodyAsString(),
					new TypeToken<List<AppointmentDetailDTO>>() {
					}.getType());

			List<String> patientIdList = getPatientIdList(appointmentList);

			List<PatientDetail> patientDetailList = chargeCaptureDAO.getPatientDetailListById(patientIdList);

			// Calculate age for each patient
			LocalDateTime now = LocalDateTime.now();
			int currentYear = now.getYear();
			int currentMonth = now.getMonthValue();

			for (PatientDetail patient : patientDetailList) {
				String dob = patient.getDateOfBirth();

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate ld = LocalDate.parse(dob, dtf);
				LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
				int dobYear = ldt.getYear();
				int dobMonth = ldt.getMonthValue();

				int age = currentYear - dobYear;
				if (dobMonth >= currentMonth) {
					age--;
				}

				patient.setAge(age);
			}

			for (AppointmentDetailDTO appointmentDetailDTO : appointmentList) {

				Optional<PatientDetail> matchingObject = patientDetailList.stream()
						.filter(p -> p.getPatientId().equals(appointmentDetailDTO.getPatientId())).findFirst();
				PatientAppointmentDetail patientAppointmentDetail = new PatientAppointmentDetail();
				patientAppointmentDetail.setAppointmentDetailDTO(appointmentDetailDTO);
				patientAppointmentDetail.setPatientDetail(matchingObject.orElse(null));
				patientAppointmentDetailList.add(patientAppointmentDetail);
			}

		} catch (IOException e) {
			LOGGER.error(Logger.EVENT_FAILURE, "IOException in getAppointments ");
			throw new ChargeCaptureServiceException("IOException in getAppointments ", e);
		} finally {
			post.releaseConnection();
		}

		return patientAppointmentDetailList;
	}

	private List<String> getPatientIdList(List<AppointmentDetailDTO> appointmentList) {

		return appointmentList.stream().map(AppointmentDetailDTO::getPatientId).collect(Collectors.toList());

	}

}
