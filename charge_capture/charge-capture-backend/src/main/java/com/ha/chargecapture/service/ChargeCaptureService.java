package com.ha.chargecapture.service;

import java.util.List;

import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;

public interface ChargeCaptureService {

	List<Facility> getFacilityDetail();

	List<PatientDetail> getPatientsForFacility(int facilityId);

	List<ICDCodes> getICDDetail();

	List<CPDCodes> getCPDDetail();

	List<PatientDetail> getPatientDetailList();

	List<PatientDetail> getPatientDetailListForWeb();

	void submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO);

	void updatePatientDetail(PatientDetail patientDetail);
}
