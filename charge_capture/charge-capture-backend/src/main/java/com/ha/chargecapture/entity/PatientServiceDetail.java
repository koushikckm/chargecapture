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
import javax.persistence.Table;

@Entity
@Table(name = "patientservicedetail")
public class PatientServiceDetail implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 69249409275425137L;

	@Id
	@GeneratedValue
	@Column(name = "service_id")
	private Integer serviceId;

	@Column(name = "patient_id")
	private String patientId;

	@Column(name = "serviced_provider_id")
	private Integer servicedProviderId;

	@Column(name = "date_of_service")
	private String dateOfService;

	@Column(name = "comments")
	private String comments;

	@Column(name = "charges")
	private Integer charges;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = PatientServiceICDCodes.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_id", referencedColumnName = "service_id")
	private List<PatientServiceICDCodes> icdCodes;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = PatientServiceCPDCodes.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "service_id", referencedColumnName = "service_id")
	private List<PatientServiceCPDCodes> cpdCodes;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getServicedProviderId() {
		return servicedProviderId;
	}

	public void setServicedProviderId(Integer servicedProviderId) {
		this.servicedProviderId = servicedProviderId;
	}

	public Integer getCharges() {
		return charges;
	}

	public void setCharges(Integer charges) {
		this.charges = charges;
	}

	public String getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public List<PatientServiceICDCodes> getIcdCodes() {
		return icdCodes;
	}

	public void setIcdCodes(List<PatientServiceICDCodes> icdCodes) {
		this.icdCodes = icdCodes;
	}

	public List<PatientServiceCPDCodes> getCpdCodes() {
		return cpdCodes;
	}

	public void setCpdCodes(List<PatientServiceCPDCodes> cpdCodes) {
		this.cpdCodes = cpdCodes;
	}

}
