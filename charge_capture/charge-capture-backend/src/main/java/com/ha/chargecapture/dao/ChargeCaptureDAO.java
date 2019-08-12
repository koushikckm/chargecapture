package com.ha.chargecapture.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ha.chargecapture.dto.PatientSearchResponseDTO;
import com.ha.chargecapture.dto.PatientsSearchDTO;
import com.ha.chargecapture.dto.UserDetailDTO;
import com.ha.chargecapture.entity.CPTCodes;
import com.ha.chargecapture.entity.CPTGroup;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.ICDGroup;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
import com.ha.chargecapture.entity.Provider;
import com.ha.chargecapture.entity.UserDetail;

public interface ChargeCaptureDAO {

	List<Facility> getFacilityDetail();

	List<PatientDetail> getPatientsForFacility(int facilityId);

	List<ICDCodes> getICDDetail();

	List<CPTCodes> getCPTDetail();

	List<PatientDetail> getPatientDetailList();
	
	Map<Integer, List<PatientSearchResponseDTO>> getPatientDetailListBySerach(PatientsSearchDTO patientsSearchDTO);
	
	long submitPatientServiceDetail(PatientServiceDetail patientServiceDetail);

	void updatePatientDetail(PatientDetail patientDetail);

	Provider getProvider(int providerId);

	void insertToPatientServiceIcdCode(int serviceId, int icdRecordId);

	void insertToPatientServiceCptCode(int serviceId, int cptRecordId);

	List<String> getFavouriteIcdsForProvider(int providerId);

	List<String> getFavouriteCptsForProvider(int providerId);

	PatientDetail getPatient(String patientId);

	List<ICDGroup> getIcdGroups();

	List<CPTGroup> getCptGroups();

	List<PatientDetail> getPatientDetailListById(List<String> patientIdList);
	
	List<PatientServiceDetail> getPatientServiceListById(List<Integer> patientIdList);
	
	List<UserDetailDTO> getUserDetailByFacilityId(int providerId);
}
