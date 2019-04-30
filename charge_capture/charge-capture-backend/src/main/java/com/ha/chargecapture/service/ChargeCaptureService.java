package com.ha.chargecapture.service;

import java.util.List;

import com.ha.chargecapture.dao.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;

public interface ChargeCaptureService {

	List<ICDCodes> getICDDetail();

	List<CPDCodes> getCPDDetail();

	List<PatientDetail> getPatientDetailList();

	void submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO);
}
