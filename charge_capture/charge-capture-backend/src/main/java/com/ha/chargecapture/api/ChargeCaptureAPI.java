package com.ha.chargecapture.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.service.ChargeCaptureService;

@RestController
@Validated
@RequestMapping("/chargecapture")
public class ChargeCaptureAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChargeCaptureAPI.class);

	@Autowired
	ChargeCaptureService chargeCaptureService;

	@GetMapping(value = "/getFacilityDetail")
	@CrossOrigin
	public List<Facility> getFacilityDetail() {
		return chargeCaptureService.getFacilityDetail();
	}

	@GetMapping(value = "/getPatientsForFacility")
	@CrossOrigin
	public List<PatientDetail> getPatientsForFacility(@RequestParam(required = true) int facilityId) {

		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientsForFacility(facilityId);

		return patientDetail;
	}

	@GetMapping(value = "/getICDCodes")
	@CrossOrigin
	public List<ICDCodes> getICDCodes() {
		return chargeCaptureService.getICDDetail();
	}

	@GetMapping(value = "/getCPDCodes")
	@CrossOrigin
	public List<CPDCodes> getCPDCodes() {
		return chargeCaptureService.getCPDDetail();
	}

	@GetMapping(value = "/getPatientDetail")
	@CrossOrigin
	public List<PatientDetail> getPatientDetail() {

		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientDetailList();

		return patientDetail;
	}

	@GetMapping(value = "/getPatientDetailForWeb")
	@CrossOrigin
	public List<PatientDetail> getPatientDetailForWeb() {

		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureService.getPatientDetailListForWeb();

		return patientDetail;
	}

	@PostMapping(value = "/submitPatientServiceDetail", produces = { "application/json" })
	@CrossOrigin
	public long submitPatientDetail(@RequestBody PatientServiceDetailDTO patientServiceDetailDTO) {

		long serviceId = chargeCaptureService.submitPatientServiceDetail(patientServiceDetailDTO);

		return serviceId;
	}

	@PutMapping(value = "/updatePatientDetail", produces = { "application/json" })
	@CrossOrigin
	public void updatePatientDetail(@RequestBody PatientDetail patientDetail) {

		chargeCaptureService.updatePatientDetail(patientDetail);
	}
}
