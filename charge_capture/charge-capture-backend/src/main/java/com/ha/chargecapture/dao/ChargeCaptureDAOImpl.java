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
import com.ha.chargecapture.entity.Facility;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;
import com.ha.chargecapture.entity.Provider;
import com.ha.chargecapture.exception.ChargeCaptureDaoException;

@Repository
@Transactional
public class ChargeCaptureDAOImpl implements ChargeCaptureDAO {

	private static final Logger LOGGER = ESAPI.getLogger(ChargeCaptureDAOImpl.class);

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
					+ "FROM chargecapturenew.patientdetail where facility_id = :facilityId";
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

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(ICDCodes.class, "icdcodes");
		icdList = criteria.list();
		if (null == icdList || icdList.isEmpty()) {
			return new ArrayList<>();
		}

		return icdList;
	}

	@Override
	public List<CPDCodes> getCPDDetail() {
		List<CPDCodes> cpdList = null;
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(CPDCodes.class, "cpdcodes");
		cpdList = criteria.list();
		if (null == cpdList || cpdList.isEmpty()) {
			return new ArrayList<>();
		}

		return cpdList;
	}

	@Override
	public List<PatientDetail> getPatientDetailList() {
		List<PatientDetail> patientdetailList = null;

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(PatientDetail.class, "patientdetail");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		patientdetailList = criteria.list();
		if (null == patientdetailList || patientdetailList.isEmpty()) {
			return new ArrayList<>();
		}

		return patientdetailList;
	}

	@Override
	public long submitPatientServiceDetail(PatientServiceDetail patientServiceDetail) {

		Session session = (Session) entityManager.getDelegate();
		session.save(patientServiceDetail);
		return patientServiceDetail.getServiceId();

	}

	@Override
	public void updatePatientDetail(PatientDetail patientDetail) {

		Session session = (Session) entityManager.getDelegate();
		session.update(patientDetail);
	}

	@Override
	public List<PatientDetail> getPatientDetailListForWeb() {

		List<PatientDetail> patientdetailList = null;

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(PatientDetail.class, "patientdetail");
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		patientdetailList = criteria.list();
		if (null == patientdetailList || patientdetailList.isEmpty()) {
			return new ArrayList<>();
		}

		return patientdetailList;
	}

	@Override
	public Provider getProvider(int providerId) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(Provider.class, "provider");
		criteria.add(Restrictions.eq("provider.providerId", providerId));

		List<Provider> providerList = criteria.list();

		Provider provider = new Provider();
		if (!providerList.isEmpty()) {
			provider = providerList.get(0);
		}
		return provider;

	}

	@Override
	public void insertToPatientServiceIcdCode(int serviceId, int icdRecordId) {

		// GET icd code for icd record id
		String icdCode = null;
		List<ICDCodes> icdList = null;

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(ICDCodes.class, "icdcodes");
		criteria.add(Restrictions.eq("icdcodes.recordId", icdRecordId));
		icdList = criteria.list();
		if (!icdList.isEmpty()) {
			icdCode = icdList.get(0).getIcdCode();
		}

		// insert to pat serv icd tbl
		Query query = session
				.createSQLQuery("insert into patientserviceicdcodes (service_id,icdcode) values (:serviceId,:icdCode)");
		query.setParameter("serviceId", serviceId);
		query.setParameter("icdCode", icdCode);
		query.executeUpdate();
	}

	@Override
	public void insertToPatientServiceCpdCode(int serviceId, int cpdRecordId) {

		// GET cpd code for cpd record id
		String cpdCode = null;
		List<CPDCodes> cpdList = null;

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(CPDCodes.class, "cpdcodes");
		criteria.add(Restrictions.eq("cpdcodes.recordId", cpdRecordId));
		cpdList = criteria.list();
		if (!cpdList.isEmpty()) {
			cpdCode = cpdList.get(0).getCpdcode();
		}

		// insert to pat serv cpd tbl
		Query query = session
				.createSQLQuery("insert into patientservicecpdcodes (service_id,cpdcode) values (:serviceId,:cpdCode)");
		query.setParameter("serviceId", serviceId);
		query.setParameter("cpdCode", cpdCode);
		query.executeUpdate();
	}

	@Override
	public PatientServiceDetail getPatientService(int serviceId) {

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(PatientServiceDetail.class, "patientservicedetail");
		criteria.add(Restrictions.eq("patientservicedetail.serviceId", serviceId));

		List<PatientServiceDetail> serviceDetailList = criteria.list();

		PatientServiceDetail patientService = new PatientServiceDetail();
		if (!serviceDetailList.isEmpty()) {
			patientService = serviceDetailList.get(0);
		}
		return patientService;
	}

	@Override
	public void updatePatientServiceStatus(PatientServiceDetail patientServiceDetail) {

		Session session = (Session) entityManager.getDelegate();

		session.update(patientServiceDetail);
	}

}
