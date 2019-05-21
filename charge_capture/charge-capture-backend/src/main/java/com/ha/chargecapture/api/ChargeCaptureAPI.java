package com.ha.chargecapture.api;

import java.util.List;

import javax.validation.Valid;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ha.chargecapture.dto.AppointmentRequestDTO;
import com.ha.chargecapture.dto.PatientAppointmentDetail;
import com.ha.chargecapture.dto.PatientDetailDTO;
import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPTCodes;
import com.ha.chargecapture.entity.CPTGroup;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.ICDGroup;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.service.AppointmentService;
import com.ha.chargecapture.service.ChargeCaptureService;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/chargecapture")
public class ChargeCaptureAPI {

	private static final Logger LOGGER = ESAPI.getLogger(ChargeCaptureAPI.class);

	@Autowired
	ChargeCaptureService chargeCaptureService;

	@Autowired
	AppointmentService appointmentService;

	@GetMapping(value = "/getFacilityDetail")
	public List<Facility> getFacilityDetail() {
		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getFacilityDetail()");
		return chargeCaptureService.getFacilityDetail();
	}

	@GetMapping(value = "/getPatientsForFacility")
	public List<PatientDetail> getPatientsForFacility(@RequestParam(required = true) int facilityId) {

		LOGGER.debug(Logger.EVENT_SUCCESS,
				"Entering ChargeCaptureAPI::getPatientsForFacility() with facility id : " + facilityId);
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientsForFacility(facilityId);

		return patientDetail;
	}

	@GetMapping(value = "/getICDCodes")
	public List<ICDCodes> getICDCodes(@RequestParam(required = false) Integer providerId) {
		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getICDCodes()");
		return chargeCaptureService.getICDDetail(providerId);
	}

	@GetMapping(value = "/getCPTCodes")
	public List<CPTCodes> getCPTCodes(@RequestParam(required = false) Integer providerId) {
		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getCPTCodes()");
		return chargeCaptureService.getCPTDetail(providerId);
	}

	@GetMapping(value = "/getPatientDetail")
	public List<PatientDetail> getPatientDetail() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getPatientDetail()");
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientDetailList();

		return patientDetail;
	}

	@PostMapping(value = "/submitPatientServiceDetail", produces = { "application/json" })
	public long submitPatientDetail(@RequestBody @Valid PatientServiceDetailDTO patientServiceDetailDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::submitPatientServiceDetail()");
		return chargeCaptureService.submitPatientServiceDetail(patientServiceDetailDTO);
	}

	@PutMapping(value = "/updatePatientDetail", produces = { "application/json" })
	public void updatePatientDetail(@RequestBody @Valid PatientDetailDTO patientDetailDto) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::updatePatientDetail()");
		chargeCaptureService.updatePatientDetails(patientDetailDto);
	}

	@GetMapping(value = "/getPatients")
	public List<PatientDetailDTO> getPatients() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getPatients()");
		List<PatientDetailDTO> patientDetail = null;

		patientDetail = chargeCaptureService.getPatients();

		return patientDetail;
	}

	@PostMapping(value = "/appointments", produces = { "application/json" })
	@CrossOrigin
	public List<PatientAppointmentDetail> getAppointments(@RequestBody AppointmentRequestDTO appointmentDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getAppointments()");
		return appointmentService.getAppointments(appointmentDTO);

	}

	@GetMapping(value = "/getIcdGroups")
	public List<ICDGroup> getIcdGroups() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getIcdGroups()");
		List<ICDGroup> icdGroups = null;

		icdGroups = chargeCaptureService.getIcdGroups();

		return icdGroups;
	}

	@GetMapping(value = "/getCptGroups")
	public List<CPTGroup> getCptGroups() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getCptGroups()");
		List<CPTGroup> cptGroups = null;

		cptGroups = chargeCaptureService.getCptGroups();

		return cptGroups;
	}
}
