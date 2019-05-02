package com.ha.chargecapture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.chargecapture.dao.ChargeCaptureDAO;
import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
import com.ha.chargecapture.entity.Provider;

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
	public List<PatientDetail> getPatientsForFacility(int facilityId) {
		return chargeCaptureDAO.getPatientsForFacility(facilityId);
	}

	@Override
	public List<Facility> getFacilityDetail() {

		return chargeCaptureDAO.getFacilityDetail();
	}

	@Override
	public List<PatientDetail> getPatientDetailListForWeb() {
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureDAO.getPatientDetailListForWeb();

		// For every patient retain only first service record

		for (int i = 0; i < patientDetail.size(); i++) {
			if (null != patientDetail.get(i).getPatientServiceDetail()
					&& !patientDetail.get(i).getPatientServiceDetail().isEmpty()) {

				List<PatientServiceDetail> patientServDetail = patientDetail.get(i).getPatientServiceDetail();
				if (null != patientServDetail && !patientServDetail.isEmpty()) {
					PatientServiceDetail servSetail = patientServDetail.get(0);
					patientServDetail.clear();
					patientServDetail.add(servSetail);
				}
			}
		}

		return patientDetail;
	}

	@Override
	public long submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO) {

		long serviceId = 0;

		PatientServiceDetail patientServiceDetail = new PatientServiceDetail();
		if (null != patientServiceDetailDTO.getPatientId()) {
			patientServiceDetail.setPatientId(patientServiceDetailDTO.getPatientId());
		}
		if (null != patientServiceDetailDTO.getDateOfService()) {
			patientServiceDetail.setDateOfService(patientServiceDetailDTO.getDateOfService());
		}
		if (null != patientServiceDetailDTO.getComments()) {
			patientServiceDetail.setComments(patientServiceDetailDTO.getComments());
		}
		if (null != patientServiceDetailDTO.getStatus()) {
			patientServiceDetail.setStatus(patientServiceDetailDTO.getStatus());
		}
		patientServiceDetail.setCharges(patientServiceDetailDTO.getCharges());

		//get provider details
		Provider provider = chargeCaptureDAO.getProvider(patientServiceDetailDTO.getProviderId());
		//set the provider
		patientServiceDetail.setProvider(provider);

		serviceId = chargeCaptureDAO.submitPatientServiceDetail(patientServiceDetail);
		Long sId = new Long(serviceId);

		if(null!=patientServiceDetailDTO.getIcdRecordIds() && !patientServiceDetailDTO.getIcdRecordIds().isEmpty()) {
			//insert to PatientServiceICDCodes with service id and cpd code
			for (int i = 0; i < patientServiceDetailDTO.getIcdRecordIds().size(); i++) {
				chargeCaptureDAO.insertToPatientServiceIcdCode(sId.intValue(),patientServiceDetailDTO.getIcdRecordIds().get(i));
			}
		}

		/*if(null!=patientServiceDetailDTO.getCpdRecordIds() && !patientServiceDetailDTO.getCpdRecordIds().isEmpty()) {
			//insert to PatientServiceCPDCodes with service id and cpd code
			for (int i = 0; i < patientServiceDetailDTO.getCpdRecordIds().size(); i++) {
				chargeCaptureDAO.insertToPatientServiceCpdCode(serviceId,patientServiceDetailDTO.getCpdRecordIds().get(i));
			}
		}*/

		return serviceId;
	}

	@Override
	public void updatePatientDetail(PatientDetail patientDetail) {

		chargeCaptureDAO.updatePatientDetail(patientDetail);
	}

}
