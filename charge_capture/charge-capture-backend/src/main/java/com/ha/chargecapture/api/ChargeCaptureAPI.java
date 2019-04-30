package com.ha.chargecapture.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ha.chargecapture.dao.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.service.ChargeCaptureService;

@RestController
@RequestMapping("/chargecapture")
public class ChargeCaptureAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChargeCaptureAPI.class);

	@Autowired
	ChargeCaptureService chargeCaptureService;

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

	@PostMapping(value = "/submitPatientServiceDetail", produces = { "application/json" })
	@CrossOrigin
	public void submitPatientDetail(@RequestBody PatientServiceDetailDTO patientServiceDetailDTO) {

		chargeCaptureService.submitPatientServiceDetail(patientServiceDetailDTO);
	}

	/*
	 * @PostMapping(value = "/updatePatientDetail", produces = { "application/json"
	 * })
	 *
	 * @CrossOrigin public void submitPatientDetail(@RequestBody List<PatientDetail>
	 * patientDetail) {
	 *
	 * chargeCaptureService.submitPatientDetail(patientServiceDetail); }
	 */
}
