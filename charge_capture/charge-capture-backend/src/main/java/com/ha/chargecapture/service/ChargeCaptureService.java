package com.ha.chargecapture.service;

import java.util.List;

import com.ha.chargecapture.dto.PatientDetailDTO;
import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPTCodes;
import com.ha.chargecapture.entity.CPTGroup;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.ICDGroup;
import com.ha.chargecapture.entity.PatientDetail;

public interface ChargeCaptureService {

	List<Facility> getFacilityDetail();

	List<PatientDetail> getPatientsForFacility(int facilityId);

	List<ICDCodes> getICDDetail(Integer providerId);

	List<CPTCodes> getCPTDetail(Integer providerId);

	List<PatientDetail> getPatientDetailList();

	long submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO);

	void updatePatientDetails(PatientDetailDTO patientDetailDto);

	List<PatientDetailDTO> getPatients();

	List<ICDGroup> getIcdGroups();

	List<CPTGroup> getCptGroups();
}
