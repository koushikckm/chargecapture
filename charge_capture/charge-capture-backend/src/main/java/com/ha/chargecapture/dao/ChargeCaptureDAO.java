package com.ha.chargecapture.dao;

import java.util.List;

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;

public interface ChargeCaptureDAO {

	List<ICDCodes> getICDDetail();

	List<CPDCodes> getCPDDetail();

	List<PatientDetail> getPatientDetailList();

	void submitPatientServiceDetail(PatientServiceDetail patientServiceDetail);
}
