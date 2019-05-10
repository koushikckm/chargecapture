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
@Table(name = "patientservicecpdcodes")
public class PatientServiceCPDCodes implements Serializable {

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
	@JoinColumn(name = "cpdcode", referencedColumnName = "cpdcode")
	private CPDCodes cpdCodes;

	// @OneToMany(fetch = FetchType.LAZY, targetEntity =
	// PatientServiceCPDModifierMapping.class, cascade = CascadeType.ALL)
	// @JoinColumn(name = "record_id", referencedColumnName =
	// "service_cpd_record_id")
	@OneToMany(targetEntity = PatientServiceCPDModifierMapping.class, mappedBy = "patientServiceCpdRecordId")
	private List<PatientServiceCPDModifierMapping> serviceCpdModifiers;

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

	public CPDCodes getCpdCodes() {
		return cpdCodes;
	}

	public void setCpdCodes(CPDCodes cpdCodes) {
		this.cpdCodes = cpdCodes;
	}

	public List<PatientServiceCPDModifierMapping> getServiceCpdModifiers() {
		return serviceCpdModifiers;
	}

	public void setServiceCpdModifiers(List<PatientServiceCPDModifierMapping> serviceCpdModifiers) {
		this.serviceCpdModifiers = serviceCpdModifiers;
	}

}
