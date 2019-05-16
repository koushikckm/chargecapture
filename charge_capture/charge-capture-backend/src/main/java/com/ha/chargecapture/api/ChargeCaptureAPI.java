package com.ha.chargecapture.api;

import java.util.List;

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

import com.ha.chargecapture.dto.AppointmentDetailDTO;
import com.ha.chargecapture.dto.AppointmentRequestDTO;
import com.ha.chargecapture.dto.CPDCodesDTO;
import com.ha.chargecapture.dto.ICDCodesDTO;
import com.ha.chargecapture.dto.PatientDetailDTO;
import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.service.AppointmentService;
import com.ha.chargecapture.service.ChargeCaptureService;

@RestController
@Validated
@RequestMapping("/chargecapture")
public class ChargeCaptureAPI {

	private static final Logger LOGGER = ESAPI.getLogger(ChargeCaptureAPI.class);

	@Autowired
	ChargeCaptureService chargeCaptureService;
	
	
	@Autowired
	AppointmentService appointmentService;

	@GetMapping(value = "/getFacilityDetail")
	@CrossOrigin
	public List<Facility> getFacilityDetail() {
		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getFacilityDetail()");
		return chargeCaptureService.getFacilityDetail();
	}

	@GetMapping(value = "/getPatientsForFacility")
	@CrossOrigin
	public List<PatientDetail> getPatientsForFacility(@RequestParam(required = true) int facilityId) {

		LOGGER.debug(Logger.EVENT_SUCCESS,
				"Entering ChargeCaptureAPI::getPatientsForFacility() with facility id : " + facilityId);
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientsForFacility(facilityId);

		return patientDetail;
	}

	@GetMapping(value = "/getICDCodes")
	@CrossOrigin
	public List<ICDCodes> getICDCodes(@RequestParam(required = false) Integer providerId) {
		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getICDCodes()");
		return chargeCaptureService.getICDDetail(providerId);
	}

	@GetMapping(value = "/getCPDCodes")
	@CrossOrigin
	public List<CPDCodes> getCPDCodes(@RequestParam(required = false) Integer providerId) {
		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getCPDCodes()");
		return chargeCaptureService.getCPDDetail(providerId);
	}

	@GetMapping(value = "/getPatientDetail")
	@CrossOrigin
	public List<PatientDetail> getPatientDetail() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getPatientDetail()");
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientDetailList();

		return patientDetail;
	}

	@GetMapping(value = "/getPatientDetailForWeb")
	@CrossOrigin
	public List<PatientDetail> getPatientDetailForWeb() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getPatientDetailForWeb()");
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientDetailListForWeb();

		return patientDetail;
	}

	@PostMapping(value = "/submitPatientServiceDetail", produces = { "application/json" })
	@CrossOrigin
	public long submitPatientDetail(@RequestBody PatientServiceDetailDTO patientServiceDetailDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::submitPatientServiceDetail()");
		return chargeCaptureService.submitPatientServiceDetail(patientServiceDetailDTO);
	}

	@PutMapping(value = "/updatePatientServiceStatus", produces = { "application/json" })
	@CrossOrigin
	public void updatePatientServiceStatus(@RequestBody PatientServiceDetailDTO patientServiceDetailDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::updatePatientServiceStatus() for service id : "
				+ patientServiceDetailDTO.getServiceId());
		chargeCaptureService.updatePatientServiceStatus(patientServiceDetailDTO);
	}

	@PutMapping(value = "/updatePatientDetail", produces = { "application/json" })
	@CrossOrigin
	public void updatePatientDetail(@RequestBody PatientDetailDTO patientDetailDto) {

		chargeCaptureService.updatePatientDetails(patientDetailDto);
	}

	@GetMapping(value = "/getPatients")
	@CrossOrigin
	public List<PatientDetailDTO> getPatients() {

		List<PatientDetailDTO> patientDetail = null;

		patientDetail = chargeCaptureService.getPatients();

		return patientDetail;
	}

	@GetMapping(value = "/getServiceForServiceId")
	@CrossOrigin
	public PatientServiceDetailDTO getServiceForServiceId(@RequestParam(required = true) int serviceId) {

		PatientServiceDetailDTO patientService = null;

		patientService = chargeCaptureService.getServiceForServiceId(serviceId);

		return patientService;
	}

	@GetMapping(value = "/getCpdsForServiceId")
	@CrossOrigin
	public List<CPDCodesDTO> getCpdsForServiceId(@RequestParam(required = true) int serviceId) {

		List<CPDCodesDTO> cpdCodes = null;

		cpdCodes = chargeCaptureService.getCpdsForServiceId(serviceId);

		return cpdCodes;
	}

	@GetMapping(value = "/getIcdsForServiceId")
	@CrossOrigin
	public List<ICDCodesDTO> getIcdsForServiceId(@RequestParam(required = true) int serviceId) {

		List<ICDCodesDTO> icdCodes = null;

		icdCodes = chargeCaptureService.getIcdsForServiceId(serviceId);

		return icdCodes;
	}
	
	
	@PostMapping(value = "/appointments", produces = { "application/json" })
	@CrossOrigin
	public List<AppointmentDetailDTO> getAppointments(@RequestBody AppointmentRequestDTO appointmentDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureAPI::getAppointments()");
		return appointmentService.getAppointments(appointmentDTO);
		
		
	
		
	}
}
