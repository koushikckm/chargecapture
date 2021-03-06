package com.ha.chargecapture.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.StringType;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.stereotype.Repository;

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.CPDGroup;
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.ICDGroup;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
import com.ha.chargecapture.entity.Provider;
import com.ha.chargecapture.exception.ChargeCaptureDaoException;

@Repository
@Transactional
public class ChargeCaptureDAOImpl implements ChargeCaptureDAO {

	private static final Logger LOGGER = ESAPI.getLogger(ChargeCaptureDAOImpl.class);

	private static final String PATIENTDETAIL_TABLE = "patientdetail";

	@PersistenceContext
	EntityManager entityManager;

	private Session getSession() {
		return (Session) entityManager.getDelegate();
	}

	@Override
	public List<Facility> getFacilityDetail() {

		List<Facility> facilityList = null;

		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(Facility.class, "facility");
			facilityList = criteria.list();
			if (null == facilityList || facilityList.isEmpty()) {
				LOGGER.debug(Logger.EVENT_SUCCESS,
						"In ChargeCaptureDAOImpl:getFacilityDetail() - facilityList is null or empty ");
				return new ArrayList<>();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getFacilityDetail ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getFacilityDetail ", cde);
		}
		return facilityList;
	}

	@Override
	public List<PatientDetail> getPatientsForFacility(int facilityId) {

		List<PatientDetail> patientdetailList = new ArrayList<>();
		try {
			Query query = null;

			String patientQuery = "SELECT patient_id AS patientId,first_name AS firstName,last_name AS lastName,middle_name AS middleName,name_suffix AS nameSuffix,date_of_birth AS dateOfBirth,gender AS gender,"
					+ "address_line_1 AS address1,address_line_2 AS address2,city AS city,state AS state,zip AS zip,home_phone AS homePhone,mobile_phone AS mobilePhone,email AS email,work_phone AS workPhone,ssn AS ssn "
					+ "FROM patientdetail where facility_id = :facilityId";
			query = getSession().createSQLQuery(patientQuery).addScalar("patientId", StringType.INSTANCE)
					.addScalar("firstName", StringType.INSTANCE).addScalar("lastName", StringType.INSTANCE)
					.addScalar("middleName", StringType.INSTANCE).addScalar("nameSuffix", StringType.INSTANCE)
					.addScalar("dateOfBirth", StringType.INSTANCE).addScalar("gender", StringType.INSTANCE)
					.addScalar("address1", StringType.INSTANCE).addScalar("address2", StringType.INSTANCE)
					.addScalar("city", StringType.INSTANCE).addScalar("state", StringType.INSTANCE)
					.addScalar("zip", StringType.INSTANCE).addScalar("homePhone", StringType.INSTANCE)
					.addScalar("mobilePhone", StringType.INSTANCE).addScalar("email", StringType.INSTANCE)
					.addScalar("workPhone", StringType.INSTANCE).addScalar("ssn", StringType.INSTANCE);

			query.setParameter("facilityId", facilityId);

			query.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] arg0, String[] arg1) {
					PatientDetail dto = new PatientDetail();
					if (null != arg0[0]) {
						dto.setPatientId(arg0[0].toString());
					}
					if (null != arg0[1]) {
						dto.setFirstName(arg0[1].toString());
					}
					if (null != arg0[2]) {
						dto.setLastName(arg0[2].toString());
					}
					if (null != arg0[3]) {
						dto.setMiddleName(arg0[3].toString());
					}
					if (null != arg0[4]) {
						dto.setNameSuffix(arg0[4].toString());
					}
					if (null != arg0[5]) {
						dto.setDateOfBirth(arg0[5].toString());
					}
					if (null != arg0[6]) {
						dto.setGender(arg0[6].toString());
					}
					if (null != arg0[7]) {
						dto.setAddressLine1(arg0[7].toString());
					}
					if (null != arg0[8]) {
						dto.setAddressLine2(arg0[8].toString());
					}
					if (null != arg0[9]) {
						dto.setCity(arg0[9].toString());
					}
					if (null != arg0[10]) {
						dto.setState(arg0[10].toString());
					}
					if (null != arg0[11]) {
						dto.setZip(arg0[11].toString());
					}
					if (null != arg0[12]) {
						dto.setHomePhone(arg0[12].toString());
					}
					if (null != arg0[13]) {
						dto.setMobilePhone(arg0[13].toString());
					}
					if (null != arg0[14]) {
						dto.setEmail(arg0[14].toString());
					}
					if (null != arg0[15]) {
						dto.setWorkPhone(arg0[15].toString());
					}
					if (null != arg0[16]) {
						dto.setSsn(arg0[16].toString());
					}
					return dto;
				}

				@SuppressWarnings("rawtypes")
				@Override
				public List transformList(List arg0) {
					List<PatientDetail> patientList = new ArrayList<>();
					for (Object objRule : arg0) {
						patientList.add((PatientDetail) objRule);
					}
					return patientList;
				}
			});

			if (!query.list().isEmpty()) {
				patientdetailList = query.list();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getPatientsForFacility ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getPatientsForFacility ", cde);
		}
		return patientdetailList;
	}

	@Override
	public List<ICDCodes> getICDDetail() {
		List<ICDCodes> icdList = null;

		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(ICDCodes.class, "icdcodes");
			icdList = criteria.list();
			if (null == icdList || icdList.isEmpty()) {
				LOGGER.debug(Logger.EVENT_SUCCESS,
						"In ChargeCaptureDAOImpl:getICDDetail() - icdList is null or empty ");
				return new ArrayList<>();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getICDDetail ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getICDDetail ", cde);
		}
		return icdList;
	}

	@Override
	public List<CPDCodes> getCPDDetail() {
		List<CPDCodes> cpdList = null;
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(CPDCodes.class, "cpdcodes");
			cpdList = criteria.list();
			if (null == cpdList || cpdList.isEmpty()) {
				LOGGER.debug(Logger.EVENT_SUCCESS,
						"In ChargeCaptureDAOImpl:getCPDDetail() - cpdList is null or empty ");
				return new ArrayList<>();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getCPDDetail ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getCPDDetail ", cde);
		}
		return cpdList;
	}

	@Override
	public List<PatientDetail> getPatientDetailList() {
		List<PatientDetail> patientdetailList = null;

		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(PatientDetail.class, PATIENTDETAIL_TABLE);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			patientdetailList = criteria.list();
			if (null == patientdetailList || patientdetailList.isEmpty()) {
				LOGGER.debug(Logger.EVENT_SUCCESS,
						"In ChargeCaptureDAOImpl:getPatientDetailList() - patientdetailList is null or empty ");
				return new ArrayList<>();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getPatientDetailList ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getPatientDetailList ", cde);
		}
		return patientdetailList;
	}

	@Override
	public long submitPatientServiceDetail(PatientServiceDetail patientServiceDetail) {

		long serviceId;
		try {
			Session session = (Session) entityManager.getDelegate();
			session.save(patientServiceDetail);
			serviceId = patientServiceDetail.getServiceId();
			LOGGER.debug(Logger.EVENT_SUCCESS,
					"In ChargeCaptureDAOImpl:submitPatientServiceDetail() - Patient service submitted successfully with serviceId -  "
							+ serviceId);
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in submitPatientServiceDetail ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in submitPatientServiceDetail ", cde);
		}
		return serviceId;

	}

	@Override
	public void updatePatientDetail(PatientDetail patientDetail) {

		try {
			Session session = (Session) entityManager.getDelegate();
			session.update(patientDetail);
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in updatePatientDetail ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in updatePatientDetail ", cde);
		}
	}

	@Override
	public Provider getProvider(int providerId) {

		Provider provider = null;
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(Provider.class, "provider");
			criteria.add(Restrictions.eq("provider.providerId", providerId));

			List<Provider> providerList = criteria.list();

			provider = new Provider();
			if (!providerList.isEmpty()) {
				provider = providerList.get(0);
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getProvider ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getProvider ", cde);
		}
		return provider;

	}

	@Override
	public void insertToPatientServiceIcdCode(int serviceId, int icdRecordId) {

		// GET icd code for icd record id
		String icdCode = null;
		List<ICDCodes> icdList = null;

		try {
			Session session = (Session) entityManager.getDelegate();

			Criteria criteria = session.createCriteria(ICDCodes.class, "icdcodes");
			criteria.add(Restrictions.eq("icdcodes.recordId", icdRecordId));
			icdList = criteria.list();
			if (!icdList.isEmpty()) {
				icdCode = icdList.get(0).getIcdCode();
			}

			// insert to pat serv icd tbl
			Query query = session.createSQLQuery(
					"insert into patientserviceicdcodes (service_id,icdcode) values (:serviceId,:icdCode)");
			query.setParameter("serviceId", serviceId);
			query.setParameter("icdCode", icdCode);
			query.executeUpdate();
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in insertToPatientServiceIcdCode ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in insertToPatientServiceIcdCode ", cde);
		}
	}

	@Override
	public void insertToPatientServiceCpdCode(int serviceId, int cpdRecordId) {

		// GET cpd code for cpd record id
		String cpdCode = null;
		List<CPDCodes> cpdList = null;

		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(CPDCodes.class, "cpdcodes");
			criteria.add(Restrictions.eq("cpdcodes.recordId", cpdRecordId));
			cpdList = criteria.list();
			if (!cpdList.isEmpty()) {
				cpdCode = cpdList.get(0).getCpdcode();
			}

			// insert to pat serv cpd tbl
			Query query = session.createSQLQuery(
					"insert into patientservicecpdcodes (service_id,cpdcode) values (:serviceId,:cpdCode)");
			query.setParameter("serviceId", serviceId);
			query.setParameter("cpdCode", cpdCode);
			query.executeUpdate();
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in insertToPatientServiceCpdCode ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in insertToPatientServiceCpdCode ", cde);
		}
	}

	@Override
	public List<String> getFavouriteIcdsForProvider(int providerId) {
		Query query = null;
		List<String> icdList = new ArrayList<>();
		try {
			String icdQuery = "SELECT picd.icdcode FROM patientserviceicdcodes picd "
					+ "JOIN patientservicedetail psd ON picd.service_id=psd.service_id and psd.provider_id=:providerId ";

			query = getSession().createSQLQuery(icdQuery);
			query.setParameter("providerId", providerId);

			if (!query.list().isEmpty()) {
				icdList = query.list();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getFavouriteIcdsForProvider ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getFavouriteIcdsForProvider ", cde);
		}
		return icdList;
	}

	@Override
	public List<String> getFavouriteCpdsForProvider(int providerId) {
		Query query = null;
		List<String> cpdList = new ArrayList<>();
		try {
			String cpdQuery = "SELECT pcpd.cpdcode FROM patientservicecpdcodes pcpd "
					+ "JOIN patientservicedetail psd ON pcpd.service_id=psd.service_id and psd.provider_id=:providerId ";

			query = getSession().createSQLQuery(cpdQuery);
			query.setParameter("providerId", providerId);

			if (!query.list().isEmpty()) {
				cpdList = query.list();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getFavouriteCpdsForProvider ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getFavouriteCpdsForProvider ", cde);
		}
		return cpdList;
	}

	@Override
	public PatientDetail getPatient(String patientId) {

		PatientDetail patient = null;
		try {
			Session session = (Session) entityManager.getDelegate();
			List<PatientDetail> patientList = null;

			Criteria criteria = session.createCriteria(PatientDetail.class, PATIENTDETAIL_TABLE);
			criteria.add(Restrictions.eq("patientdetail.patientId", patientId));
			patientList = criteria.list();
			if (null != patientList && !patientList.isEmpty()) {
				patient = patientList.get(0);
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getPatient ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getPatient ", cde);
		}
		return patient;
	}

	@Override
	public List<ICDGroup> getIcdGroups() {

		List<ICDGroup> icdGroups = null;
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(ICDGroup.class, "icdgroup");
			icdGroups = criteria.list();
			if (null == icdGroups || icdGroups.isEmpty()) {
				LOGGER.debug(Logger.EVENT_SUCCESS,
						"In ChargeCaptureDAOImpl:getIcdGroups() - icdGroups is null or empty ");
				return new ArrayList<>();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getIcdGroups ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getIcdGroups ", cde);
		}
		return icdGroups;
	}

	@Override
	public List<CPDGroup> getCpdGroups() {

		List<CPDGroup> cpdGroups = null;
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(CPDGroup.class, "cpdgroup");
			cpdGroups = criteria.list();
			if (null == cpdGroups || cpdGroups.isEmpty()) {
				LOGGER.debug(Logger.EVENT_SUCCESS,
						"In ChargeCaptureDAOImpl:getCpdGroups() - cpdGroups is null or empty ");
				return new ArrayList<>();
			}
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getCpdGroups ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getCpdGroups ", cde);
		}
		return cpdGroups;
	}

	@Override
	public List<PatientDetail> getPatientDetailListById(List<String> patientIdList) {

		List<PatientDetail> patientList = null;
		try {
			Session session = (Session) entityManager.getDelegate();
			Criteria criteria = session.createCriteria(PatientDetail.class, PATIENTDETAIL_TABLE);
			criteria.add(Restrictions.in("patientdetail.patientId", patientIdList));
			patientList = criteria.list();
		} catch (ChargeCaptureDaoException cde) {
			LOGGER.error(Logger.EVENT_FAILURE, "ChargeCaptureDaoException in getPatientDetailListById ", cde);
			throw new ChargeCaptureDaoException("ChargeCaptureDaoException in getPatientDetailListById ", cde);
		}
		return patientList;
	}

}
