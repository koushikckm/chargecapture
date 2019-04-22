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

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
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

	@GetMapping(value = "/getPatientDetailList")
	@CrossOrigin
	public List<PatientDetail> getPatientDetailList() {
		return chargeCaptureService.getPatientDetailList();
	}

	@GetMapping(value = "/getPatientDetail")
	@CrossOrigin
	public String getPatientDetail() {

		String jsonString = "[{ \"PatientID\": \"10006699\", \"MRN\": \"1231231\", \"Gender\": \"Male\", \"DOB\": \"2019-04-17T00:00:00\", \"DOS\": \"2019-04-17T00:00:00\", \"RefPhysician\": \"provider\", \"BillingPhysician \": \"provider11\", \"CaseOrigin\": \"case\", \"Facility\": \"1\", \"Diagnosis\": \"diagnosis\", \"Procedure\": \"procedure\", \"Notes\": \"note\", \"Status\": 0 }, { \"PatientID\": \"10006698\", \"MRN\": \"1231230\", \"Gender\": \"Female\", \"DOB\": \"2018-04-17T00:00:00\", \"DOS\": \"2018-04-17T00:00:00\", \"RefPhysician\": \"provider1\", \"BillingPhysician \": \"provider12\", \"CaseOrigin\": \"case1\", \"Facility\": \"2\", \"Diagnosis\": \"diagnosis1\", \"Procedure\": \"procedure1\", \"Notes\": \"note1\", \"Status\": 0 }, { \"PatientID\": \"10006697\", \"MRN\": \"1231229\", \"Gender\": \"Male\", \"DOB\": \"2017-04-17T00:00:00\", \"DOS\": \"2017-04-17T00:00:00\", \"RefPhysician\": \"provider2\", \"BillingPhysician \": \"provider13\", \"CaseOrigin\": \"case2\", \"Facility\": \"3\", \"Diagnosis\": \"diagnosis2\", \"Procedure\": \"procedure2\", \"Notes\": \"note2\", \"Status\": 1 }, { \"PatientID\": \"10006696\", \"MRN\": \"1231228\", \"Gender\": \"Female\", \"DOB\": \"2016-04-17T00:00:00\", \"DOS\": \"2016-04-17T00:00:00\", \"RefPhysician\": \"provider3\", \"BillingPhysician \": \"provider14\", \"CaseOrigin\": \"case3\", \"Facility\": \"1\", \"Diagnosis\": \"diagnosis3\", \"Procedure\": \"procedure3\", \"Notes\": \"note3\", \"Status\": 0 }, { \"PatientID\": \"10006695\", \"MRN\": \"1231227\", \"Gender\": \"Male\", \"DOB\": \"2015-04-17T00:00:00\", \"DOS\": \"2015-04-17T00:00:00\", \"RefPhysician\": \"provider4\", \"BillingPhysician \": \"provider15\", \"CaseOrigin\": \"case4\", \"Facility\": \"2\", \"Diagnosis\": \"diagnosis4\", \"Procedure\": \"procedure4\", \"Notes\": \"note4\", \"Status\": 1 } ]";
		return jsonString;
	}

	@PostMapping(value = "/submitPatientDetail", produces = { "application/json" })
	@CrossOrigin
	public void submitPatientDetail(@RequestBody PatientServiceDetail patientServiceDetail) {

		chargeCaptureService.submitPatientDetail(patientServiceDetail);
	}

}
