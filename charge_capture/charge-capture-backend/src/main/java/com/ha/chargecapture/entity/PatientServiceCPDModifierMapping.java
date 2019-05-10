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
@Table(name = "patientservicecpdmodifiermapping")
public class PatientServiceCPDModifierMapping implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8683418737095768147L;

	@Id
	@Column(name = "record_id")
	private int id;

	@Column(name = "service_cpd_record_id", unique = true, nullable = false)
	private Integer patientServiceCpdRecordId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "modifier_code", referencedColumnName = "modifier_code")
	private CPDModifiers cpdModifiers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPatientServiceCpdRecordId() {
		return patientServiceCpdRecordId;
	}

	public void setPatientServiceCpdRecordId(Integer patientServiceCpdRecordId) {
		this.patientServiceCpdRecordId = patientServiceCpdRecordId;
	}

	public CPDModifiers getCpdModifiers() {
		return cpdModifiers;
	}

	public void setCpdModifiers(CPDModifiers cpdModifiers) {
		this.cpdModifiers = cpdModifiers;
	}

}
