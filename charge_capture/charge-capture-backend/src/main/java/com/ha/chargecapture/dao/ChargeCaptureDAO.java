package com.ha.chargecapture.dao;

import java.util.List;

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;

public interface ChargeCaptureDAO {

	List<Facility> getFacilityDetail();

	List<PatientDetail> getPatientsForFacility(int facilityId);

	List<ICDCodes> getICDDetail();

	List<CPDCodes> getCPDDetail();

	List<PatientDetail> getPatientDetailList();

	List<PatientDetail> getPatientDetailListForWeb();

	void submitPatientServiceDetail(PatientServiceDetail patientServiceDetail);

	void updatePatientDetail(PatientDetail patientDetail);
}
