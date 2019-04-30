package com.ha.chargecapture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.chargecapture.dao.ChargeCaptureDAO;
import com.ha.chargecapture.dao.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceCPDCodes;
import com.ha.chargecapture.entity.PatientServiceDetail;

@Service
public class ChargeCaptureServiceImpl implements ChargeCaptureService {

	@Autowired
	ChargeCaptureDAO chargeCaptureDAO;

	@Override
	public List<ICDCodes> getICDDetail() {
		return chargeCaptureDAO.getICDDetail();
	}

	@Override
	public List<CPDCodes> getCPDDetail() {
		return chargeCaptureDAO.getCPDDetail();
	}

	@Override
	public List<PatientDetail> getPatientDetailList() {
		return chargeCaptureDAO.getPatientDetailList();
	}

	@Override
	public void submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO) {

		PatientServiceDetail patientServiceDetail = new PatientServiceDetail();
		patientServiceDetail.setPatientId(patientServiceDetailDTO.getPatientId());
		patientServiceDetail.setDateOfService(patientServiceDetailDTO.getDateOfService());
		patientServiceDetail.setComments(patientServiceDetailDTO.getComments());
		patientServiceDetail.setStatus(patientServiceDetailDTO.getStatus());
		patientServiceDetail.setCharges(patientServiceDetailDTO.getCharges());

		List<PatientServiceCPDCodes> cpdCodes = new ArrayList<>();

		for (int i = 0; i < patientServiceDetailDTO.getCpdRecordIds().size(); i++) {

		}
		patientServiceDetail.setCpdCodes(cpdCodes);

		// patientServiceDetail.setIcdCodes(icdCodes);

		// patientServiceDetail.setProvider(provider);

		chargeCaptureDAO.submitPatientServiceDetail(patientServiceDetail);
	}

}
