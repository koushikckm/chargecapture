package com.ha.chargecapture.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

		List<PatientDetail> patientDetail = chargeCaptureDAO.getPatientsForFacility(facilityId);

		LocalDateTime now = LocalDateTime.now();
		int currentYear = now.getYear();
		int currentMonth = now.getMonthValue();

		// calculate age for every patient
		for (PatientDetail patient : patientDetail) {

			String dob = patient.getDateOfBirth();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate ld = LocalDate.parse(dob, dtf);
			LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
			int dobYear = ldt.getYear();
			int dobMonth = ldt.getMonthValue();

			int age = currentYear - dobYear;
			if (dobMonth >= currentMonth) {
				age--;
			}

			patient.setAge(age);
		}

		return patientDetail;
	}

	@Override
	public List<Facility> getFacilityDetail() {

		return chargeCaptureDAO.getFacilityDetail();
	}

	@Override
	public List<PatientDetail> getPatientDetailListForWeb() {
		List<PatientDetail> patientDetail = null;

		patientDetail = chargeCaptureDAO.getPatientDetailListForWeb();

		for (int i = 0; i < patientDetail.size(); i++) {
			if (null != patientDetail.get(i).getPatientServiceDetail()
					&& !patientDetail.get(i).getPatientServiceDetail().isEmpty()) {

				// remove service record if its not pending review
				/*
				 * List<PatientServiceDetail> patientServDetail =
				 * patientDetail.get(i).getPatientServiceDetail(); for(int
				 * j=0;j<patientServDetail.size();j++) {
				 * if(!patientServDetail.get(j).getStatus().equalsIgnoreCase("PendingReview")) {
				 * //remove patientServDetail.remove(j); } }
				 */

				// retain only first serv record

				List<PatientServiceDetail> patientServDetail = patientDetail.get(i).getPatientServiceDetail();
				if (null != patientServDetail && !patientServDetail.isEmpty()) {
					PatientServiceDetail servDetail = patientServDetail.get(0);
					patientServDetail.clear();
					patientServDetail.add(servDetail);

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

		// get provider details
		Provider provider = chargeCaptureDAO.getProvider(patientServiceDetailDTO.getProviderId());
		// set the provider
		patientServiceDetail.setProvider(provider);

		serviceId = chargeCaptureDAO.submitPatientServiceDetail(patientServiceDetail);
		Long sId = new Long(serviceId);

		if (null != patientServiceDetailDTO.getIcdRecordIds() && !patientServiceDetailDTO.getIcdRecordIds().isEmpty()) {
			// insert to PatientServiceICDCodes with service id and cpd code
			for (int i = 0; i < patientServiceDetailDTO.getIcdRecordIds().size(); i++) {
				chargeCaptureDAO.insertToPatientServiceIcdCode(sId.intValue(),
						patientServiceDetailDTO.getIcdRecordIds().get(i));
			}
		}

		if (null != patientServiceDetailDTO.getCpdRecordIds() && !patientServiceDetailDTO.getCpdRecordIds().isEmpty()) {
			// insert to PatientServiceCPDCodes with service id and cpd code
			for (int i = 0; i < patientServiceDetailDTO.getCpdRecordIds().size(); i++) {
				chargeCaptureDAO.insertToPatientServiceCpdCode(sId.intValue(),
						patientServiceDetailDTO.getCpdRecordIds().get(i));
			}
		}

		return serviceId;
	}

	@Override
	public void updatePatientDetail(PatientDetail patientDetail) {

		chargeCaptureDAO.updatePatientDetail(patientDetail);
	}

	@Override
	public void updatePatientServiceStatus(PatientServiceDetailDTO patientServiceDetailDTO) {

		PatientServiceDetail patientServiceDetail = chargeCaptureDAO
				.getPatientService(patientServiceDetailDTO.getServiceId());
		patientServiceDetail.setStatus(patientServiceDetailDTO.getStatus());

		chargeCaptureDAO.updatePatientServiceStatus(patientServiceDetail);
	}

}
