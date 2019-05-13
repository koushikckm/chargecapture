package com.ha.chargecapture.service;

import java.util.List;

import com.ha.chargecapture.dto.CPDCodesDTO;
import com.ha.chargecapture.dto.ICDCodesDTO;
import com.ha.chargecapture.dto.PatientDetailDTO;
import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;

public interface ChargeCaptureService {

	List<Facility> getFacilityDetail();

	List<PatientDetail> getPatientsForFacility(int facilityId);

	List<ICDCodes> getICDDetail(Integer providerId);

	List<CPDCodes> getCPDDetail(Integer providerId);

	List<PatientDetail> getPatientDetailList();

	List<PatientDetail> getPatientDetailListForWeb();

	long submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO);

	void updatePatientDetail(PatientDetail patientDetail);

	void updatePatientDetails(PatientDetailDTO patientDetailDto);

	void updatePatientServiceStatus(PatientServiceDetailDTO patientServiceDetailDTO);

	List<PatientDetailDTO> getPatients();

	PatientServiceDetailDTO getServiceForServiceId(int serviceId);

	List<CPDCodesDTO> getCpdsForServiceId(int serviceId);

	List<ICDCodesDTO> getIcdsForServiceId(int serviceId);
}
