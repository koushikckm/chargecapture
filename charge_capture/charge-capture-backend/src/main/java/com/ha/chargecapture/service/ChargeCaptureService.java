package com.ha.chargecapture.service;

import java.util.List;

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;

public interface ChargeCaptureService {

	List<ICDCodes> getICDDetail();

	List<CPDCodes> getCPDDetail();

	List<PatientDetail> getPatientDetailList();

	void submitPatientDetail(PatientServiceDetail patientServiceDetail);
}
