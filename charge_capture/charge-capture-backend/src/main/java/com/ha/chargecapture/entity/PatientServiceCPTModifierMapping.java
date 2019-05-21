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
@Table(name = "patientservicecptmodifiermapping")
public class PatientServiceCPTModifierMapping implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8683418737095768147L;

	@Id
	@Column(name = "record_id")
	private int id;

	@Column(name = "service_cpt_record_id", unique = true, nullable = false)
	private Integer patientServiceCptRecordId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "modifier_code", referencedColumnName = "modifier_code")
	private CPTModifiers cptModifiers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPatientServiceCptRecordId() {
		return patientServiceCptRecordId;
	}

	public void setPatientServiceCptRecordId(Integer patientServiceCptRecordId) {
		this.patientServiceCptRecordId = patientServiceCptRecordId;
	}

	public CPTModifiers getCptModifiers() {
		return cptModifiers;
	}

	public void setCptModifiers(CPTModifiers cptModifiers) {
		this.cptModifiers = cptModifiers;
	}

}
