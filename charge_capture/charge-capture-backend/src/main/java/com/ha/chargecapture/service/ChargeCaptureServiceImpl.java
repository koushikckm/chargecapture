package com.ha.chargecapture.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
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
import com.ha.chargecapture.exception.ChargeCaptureServiceException;

@Service
public class ChargeCaptureServiceImpl implements ChargeCaptureService {

	private static final Logger LOGGER = ESAPI.getLogger(ChargeCaptureServiceImpl.class);

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

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getPatientsForFacility() ");
		List<PatientDetail> patientDetail = null;
		try {
			patientDetail = chargeCaptureDAO.getPatientsForFacility(facilityId);

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
		} catch (ChargeCaptureServiceException ce) {
			LOGGER.error(Logger.EVENT_FAILURE, "Exception in getPatientsForFacility()", ce);
			throw new ChargeCaptureServiceException("Exception in getPatientsForFacility()", ce);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::getPatientsForFacility() ");
		return patientDetail;
	}

	@Override
	public List<Facility> getFacilityDetail() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getFacilityDetail() ");
		return chargeCaptureDAO.getFacilityDetail();
	}

	@Override
	public List<PatientDetail> getPatientDetailListForWeb() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getPatientDetailListForWeb() ");
		List<PatientDetail> patientDetail = null;

		try {
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
		} catch (ChargeCaptureServiceException ce) {
			LOGGER.error(Logger.EVENT_FAILURE, "Exception in getPatientDetailListForWeb()", ce);
			throw new ChargeCaptureServiceException("Exception in getPatientDetailListForWeb()", ce);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::getPatientDetailListForWeb() ");
		return patientDetail;
	}

	@Override
	public long submitPatientServiceDetail(PatientServiceDetailDTO patientServiceDetailDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::submitPatientServiceDetail() ");
		long serviceId = 0;

		try {

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

			if (null != patientServiceDetailDTO.getIcdRecordIds()
					&& !patientServiceDetailDTO.getIcdRecordIds().isEmpty()) {
				// insert to PatientServiceICDCodes with service id and cpd code
				for (int i = 0; i < patientServiceDetailDTO.getIcdRecordIds().size(); i++) {
					chargeCaptureDAO.insertToPatientServiceIcdCode(sId.intValue(),
							patientServiceDetailDTO.getIcdRecordIds().get(i));
				}
			}

			if (null != patientServiceDetailDTO.getCpdRecordIds()
					&& !patientServiceDetailDTO.getCpdRecordIds().isEmpty()) {
				// insert to PatientServiceCPDCodes with service id and cpd code
				for (int i = 0; i < patientServiceDetailDTO.getCpdRecordIds().size(); i++) {
					chargeCaptureDAO.insertToPatientServiceCpdCode(sId.intValue(),
							patientServiceDetailDTO.getCpdRecordIds().get(i));
				}
			}
		} catch (ChargeCaptureServiceException ce) {
			LOGGER.error(Logger.EVENT_FAILURE, "Exception in submitPatientServiceDetail()", ce);
			throw new ChargeCaptureServiceException("Exception in submitPatientServiceDetail()", ce);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS,
				"Exiting ChargeCaptureServiceImpl::submitPatientServiceDetail(), Created service id : " + serviceId);
		return serviceId;
	}

	@Override
	public void updatePatientDetail(PatientDetail patientDetail) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::updatePatientDetail() ");
		chargeCaptureDAO.updatePatientDetail(patientDetail);
	}

	@Override
	public void updatePatientServiceStatus(PatientServiceDetailDTO patientServiceDetailDTO) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::updatePatientServiceStatus() ");
		PatientServiceDetail patientServiceDetail = null;
		try {
			patientServiceDetail = chargeCaptureDAO.getPatientService(patientServiceDetailDTO.getServiceId());
			patientServiceDetail.setStatus(patientServiceDetailDTO.getStatus());

			chargeCaptureDAO.updatePatientServiceStatus(patientServiceDetail);
		} catch (ChargeCaptureServiceException ce) {
			LOGGER.error(Logger.EVENT_FAILURE, "Exception in updatePatientServiceStatus()", ce);
			throw new ChargeCaptureServiceException("Exception in updatePatientServiceStatus()", ce);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::updatePatientServiceStatus() ");
	}

}
