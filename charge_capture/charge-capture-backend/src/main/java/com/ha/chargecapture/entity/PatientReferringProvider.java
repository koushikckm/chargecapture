package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patientreferringprovidermapping")
public class PatientReferringProvider implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7226045995975365837L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "patient_id")
	private String patientId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id", referencedColumnName = "provider_id")
	private ReferringProvider referringProvider;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public ReferringProvider getReferringProvider() {
		return referringProvider;
	}

	public void setReferringProvider(ReferringProvider referringProvider) {
		this.referringProvider = referringProvider;
	}

}
