package com.ha.chargecapture.dao;

import java.util.List;

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.CPDGroup;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.ICDGroup;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
import com.ha.chargecapture.entity.Provider;

public interface ChargeCaptureDAO {

	List<Facility> getFacilityDetail();

	List<PatientDetail> getPatientsForFacility(int facilityId);

	List<ICDCodes> getICDDetail();

	List<CPDCodes> getCPDDetail();

	List<PatientDetail> getPatientDetailList();

	long submitPatientServiceDetail(PatientServiceDetail patientServiceDetail);

	void updatePatientDetail(PatientDetail patientDetail);

	Provider getProvider(int providerId);

	void insertToPatientServiceIcdCode(int serviceId, int icdRecordId);

	void insertToPatientServiceCpdCode(int serviceId, int cpdRecordId);

	List<String> getFavouriteIcdsForProvider(int providerId);

	List<String> getFavouriteCpdsForProvider(int providerId);

	PatientDetail getPatient(String patientId);

	List<ICDGroup> getIcdGroups();

	List<CPDGroup> getCpdGroups();

	List<PatientDetail> getPatientDetailListById(List<String> patientIdList);
}
