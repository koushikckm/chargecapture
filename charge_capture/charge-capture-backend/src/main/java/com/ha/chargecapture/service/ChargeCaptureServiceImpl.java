package com.ha.chargecapture.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.chargecapture.dao.ChargeCaptureDAO;
import com.ha.chargecapture.dto.PatientDetailDTO;
import com.ha.chargecapture.dto.PatientServiceDetailDTO;
import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.CPDGroup;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.ICDGroup;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
import com.ha.chargecapture.entity.Provider;
import com.ha.chargecapture.exception.ChargeCaptureDaoException;
import com.ha.chargecapture.exception.ChargeCaptureServiceException;

@Service
public class ChargeCaptureServiceImpl implements ChargeCaptureService {

	private static final Logger LOGGER = ESAPI.getLogger(ChargeCaptureServiceImpl.class);

	@Autowired
	ChargeCaptureDAO chargeCaptureDAO;

	@Override
	public List<ICDCodes> getICDDetail(Integer providerId) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getICDDetail() ");
		List<ICDCodes> icdList = chargeCaptureDAO.getICDDetail();

		if (null != providerId) {
			icdList = setFavouriteIcdsForProvider(icdList, providerId);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::getICDDetail() ");
		return icdList;
	}

	@Override
	public List<CPDCodes> getCPDDetail(Integer providerId) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getCPDDetail() ");
		List<CPDCodes> cpdList = chargeCaptureDAO.getCPDDetail();

		if (null != providerId) {
			cpdList = setFavouriteCpdsForProvider(cpdList, providerId);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::getCPDDetail() ");
		return cpdList;
	}

	@Override
	public List<PatientDetail> getPatientDetailList() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getPatientDetailList() ");
		return chargeCaptureDAO.getPatientDetailList();
	}

	@Override
	public List<PatientDetail> getPatientsForFacility(int facilityId) {

		LOGGER.debug(Logger.EVENT_SUCCESS,
				"Entering ChargeCaptureServiceImpl::getPatientsForFacility() with facility id - " + facilityId);
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
	public void updatePatientDetails(PatientDetailDTO patientDetailDto) {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::updatePatientDetail() ");
		try {
			// get the patient detail
			PatientDetail patient = chargeCaptureDAO.getPatient(patientDetailDto.getPatientId());

			// set patient details to be updated
			patient.setFirstName(patientDetailDto.getFirstName());
			patient.setLastName(patientDetailDto.getLastName());
			patient.setMiddleName(patientDetailDto.getMiddleName());
			patient.setDateOfBirth(patientDetailDto.getDateOfBirth());
			patient.setGender(patientDetailDto.getGender());
			patient.setAddressLine1(patientDetailDto.getAddressLine1());
			patient.setAddressLine2(patientDetailDto.getAddressLine2());
			patient.setCity(patientDetailDto.getCity());
			patient.setState(patientDetailDto.getState());
			patient.setZip(patientDetailDto.getZip());
			patient.setHomePhone(patientDetailDto.getHomePhone());
			patient.setMobilePhone(patientDetailDto.getMobilePhone());
			patient.setEmail(patientDetailDto.getEmail());
			patient.setWorkPhone(patientDetailDto.getWorkPhone());
			patient.setChartNumber(patientDetailDto.getChartNumber());
			patient.setSsn(patientDetailDto.getSsn());
			patient.setAge(patientDetailDto.getAge());

			// Changes to update icd and cpd detail

			chargeCaptureDAO.updatePatientDetail(patient);
		} catch (ChargeCaptureServiceException cse) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureServiceException in updatePatientDetails ", cse);
			throw new ChargeCaptureDaoException("ChargeCaptureServiceException in updatePatientDetails ", cse);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::updatePatientDetail() ");
	}

	@Override
	public List<PatientDetailDTO> getPatients() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getPatients() ");
		List<PatientDetailDTO> patientList = new ArrayList<>();
		try {
			List<PatientDetail> patientDetailList = chargeCaptureDAO.getPatientDetailList();

			for (int i = 0; i < patientDetailList.size(); i++) {
				PatientDetailDTO patient = new PatientDetailDTO();
				PatientDetail patientDetail = patientDetailList.get(i);
				if (null != patientDetail.getPatientId()) {
					patient.setPatientId(patientDetail.getPatientId());
				}
				if (null != patientDetail.getFirstName()) {
					patient.setFirstName(patientDetail.getFirstName());
				}
				if (null != patientDetail.getLastName()) {
					patient.setLastName(patientDetail.getLastName());
				}
				if (null != patientDetail.getMiddleName()) {
					patient.setMiddleName(patientDetail.getMiddleName());
				}
				if (null != patientDetail.getNameSuffix()) {
					patient.setNameSuffix(patientDetail.getNameSuffix());
				}
				if (null != patientDetail.getGender()) {
					patient.setGender(patientDetail.getGender());
				}
				if (null != patientDetail.getAddressLine1()) {
					patient.setAddressLine1(patientDetail.getAddressLine1());
				}
				if (null != patientDetail.getAddressLine2()) {
					patient.setAddressLine2(patientDetail.getAddressLine2());
				}
				if (null != patientDetail.getCity()) {
					patient.setCity(patientDetail.getCity());
				}
				if (null != patientDetail.getState()) {
					patient.setState(patientDetail.getState());
				}
				if (null != patientDetail.getZip()) {
					patient.setZip(patientDetail.getZip());
				}
				if (null != patientDetail.getHomePhone()) {
					patient.setHomePhone(patientDetail.getHomePhone());
				}
				if (null != patientDetail.getMobilePhone()) {
					patient.setMobilePhone(patientDetail.getMobilePhone());
				}
				if (null != patientDetail.getEmail()) {
					patient.setEmail(patientDetail.getEmail());
				}
				if (null != patientDetail.getWorkPhone()) {
					patient.setWorkPhone(patientDetail.getWorkPhone());
				}
				if (null != patientDetail.getPrimaryLanguage()) {
					patient.setPrimaryLanguage(patientDetail.getPrimaryLanguage());
				}
				if (null != patientDetail.getSsn()) {
					patient.setSsn(patientDetail.getSsn());
				}
				if (null != patientDetail.getIsProcessed()) {
					patient.setIsProcessed(patientDetail.getIsProcessed());
				}
				patient.setAge(patientDetail.getAge());

				List<PatientServiceDetail> patientServDetails = patientDetailList.get(i).getPatientServiceDetail();
				List<Integer> servIds = new ArrayList<>();

				for (int j = 0; j < patientServDetails.size(); j++) {
					servIds.add(patientServDetails.get(j).getServiceId());
					patient.setServiceId(patientServDetails.get(j).getServiceId());

				}
				patient.setServiceIds(servIds);

				patientList.add(patient);

			}
		} catch (ChargeCaptureServiceException cse) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureServiceException in getPatients ", cse);
			throw new ChargeCaptureDaoException("ChargeCaptureServiceException in getPatients ", cse);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::getPatients() ");
		return patientList;
	}

	public List<ICDCodes> setFavouriteIcdsForProvider(List<ICDCodes> icdList, Integer providerId) {

		LOGGER.debug(Logger.EVENT_SUCCESS,
				"Entering ChargeCaptureServiceImpl::setFavouriteIcdsForProvider() with providerId - " + providerId);
		try {
			List<String> icds = chargeCaptureDAO.getFavouriteIcdsForProvider(providerId);
			if (!icds.isEmpty()) {
				Map<String, Integer> icdMap = new HashMap<>();
				for (String icd : icds) {
					Integer count = icdMap.get(icd);
					icdMap.put(icd, (count == null) ? 1 : count + 1);
				}

				// sort the map in descending order of count
				LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
				icdMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
						.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

				// get top 5 from map and put it to list
				int mapCount = 0;
				icds.clear();
				for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
					if (mapCount > 4) {
						break;
					}
					icds.add(entry.getKey());
					mapCount++;
				}
			}

			// if icddlist has icds set fav flag as 1
			for (int i = 0; i < icdList.size(); i++) {
				for (String s : icds) {
					if (icdList.get(i).getIcdCode().equals(s)) {
						icdList.get(i).setFavouriteForProvider(true);
					}
				}
			}
		} catch (ChargeCaptureServiceException cse) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureServiceException in setFavouriteIcdsForProvider ", cse);
			throw new ChargeCaptureDaoException("ChargeCaptureServiceException in setFavouriteIcdsForProvider ", cse);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::setFavouriteIcdsForProvider() ");
		return icdList;
	}

	public List<CPDCodes> setFavouriteCpdsForProvider(List<CPDCodes> cpdList, Integer providerId) {

		LOGGER.debug(Logger.EVENT_SUCCESS,
				"Entering ChargeCaptureServiceImpl::setFavouriteCpdsForProvider() with providerId - " + providerId);
		try {
			List<String> cpds = chargeCaptureDAO.getFavouriteCpdsForProvider(providerId);
			if (!cpds.isEmpty()) {
				Map<String, Integer> cpdMap = new HashMap<>();
				for (String cpd : cpds) {
					Integer count = cpdMap.get(cpd);
					cpdMap.put(cpd, (count == null) ? 1 : count + 1);
				}

				// sort the map in descending order of count
				LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
				cpdMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
						.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

				// get top 5 from map and put it to list
				int mapCount = 0;
				cpds.clear();
				for (Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()) {
					if (mapCount > 4) {
						break;
					}
					cpds.add(entry.getKey());
					mapCount++;
				}
			}

			// if cpddlist has cpds set fav flag as 1
			for (int i = 0; i < cpdList.size(); i++) {
				for (String s : cpds) {
					if (cpdList.get(i).getCpdcode().equals(s)) {
						cpdList.get(i).setFavouriteForProvider(true);
					}
				}
			}
		} catch (ChargeCaptureServiceException cse) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureServiceException in setFavouriteCpdsForProvider ", cse);
			throw new ChargeCaptureDaoException("ChargeCaptureServiceException in setFavouriteCpdsForProvider ", cse);
		}
		LOGGER.debug(Logger.EVENT_SUCCESS, "Exiting ChargeCaptureServiceImpl::setFavouriteCpdsForProvider() ");
		return cpdList;
	}

	@Override
	public List<ICDGroup> getIcdGroups() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getIcdGroups() ");
		return chargeCaptureDAO.getIcdGroups();
	}

	@Override
	public List<CPDGroup> getCpdGroups() {

		LOGGER.debug(Logger.EVENT_SUCCESS, "Entering ChargeCaptureServiceImpl::getCpdGroups() ");
		return chargeCaptureDAO.getCpdGroups();
	}
}
