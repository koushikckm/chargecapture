package com.ha.chargecapture.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ha.chargecapture.entity.CPDCodes;
import com.ha.chargecapture.entity.ICDCodes;
import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceDetail;

@Repository
public class ChargeCaptureDAOImpl implements ChargeCaptureDAO {

	@PersistenceContext
	EntityManager entityManager;

	private Session getSession() {
		return (Session) entityManager.getDelegate();
	}

	@Override
	public List<ICDCodes> getICDDetail() {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(ICDCodes.class, "icdcodes");
		List<ICDCodes> icdList = criteria.list();
		if (null == icdList || icdList.isEmpty()) {
			return new ArrayList<>();
		}
		return icdList;
	}

	@Override
	public List<CPDCodes> getCPDDetail() {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(CPDCodes.class, "cpdcodes");
		List<CPDCodes> cpdList = criteria.list();
		if (null == cpdList || cpdList.isEmpty()) {
			return new ArrayList<>();
		}
		return cpdList;
	}

	@Override
	public List<PatientDetail> getPatientDetailList() {
		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(PatientDetail.class, "patientdetail");
		List<PatientDetail> patientdetailList = criteria.list();
		if (null == patientdetailList || patientdetailList.isEmpty()) {
			return new ArrayList<>();
		}
		return patientdetailList;
	}

	@Override
	public void submitPatientDetail(PatientServiceDetail patientServiceDetail) {
		Session session = (Session) entityManager.getDelegate();
		session.save(patientServiceDetail);
	}

}
