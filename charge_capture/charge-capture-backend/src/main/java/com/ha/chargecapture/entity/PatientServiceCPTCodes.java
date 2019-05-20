package com.ha.chargecapture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patientservicecptcodes")
public class PatientServiceCPTCodes implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3752124988860146075L;

	@Id
	@GeneratedValue
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "service_id", unique = true, nullable = false)
	private Integer serviceId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cptcode", referencedColumnName = "cptcode")
	private CPTCodes cptCodes;

	@OneToMany(targetEntity = PatientServiceCPTModifierMapping.class, mappedBy = "patientServiceCptRecordId")
	private List<PatientServiceCPTModifierMapping> serviceCptModifiers;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public CPTCodes getCptCodes() {
		return cptCodes;
	}

	public void setCptCodes(CPTCodes cptCodes) {
		this.cptCodes = cptCodes;
	}

	public List<PatientServiceCPTModifierMapping> getServiceCptModifiers() {
		return serviceCptModifiers;
	}

	public void setServiceCptModifiers(List<PatientServiceCPTModifierMapping> serviceCptModifiers) {
		this.serviceCptModifiers = serviceCptModifiers;
	}

}
