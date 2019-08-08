package com.ha.chargecapture.dto;

import java.util.Date;
import java.util.List;

import com.ha.chargecapture.entity.PatientDetail;
import com.ha.chargecapture.entity.PatientServiceCPTCodes;
import com.ha.chargecapture.entity.PatientServiceICDCodes;
import com.ha.chargecapture.entity.Provider;

public class PatientSearchResponseDTO {
	private Integer serviceId;

	private String patientId;

	private Date dateOfBirth;
	
	private Date dateOfService;

	private String comments;

	private String status;
	
	private String providerName;
	
	private String patientName;
	
	private String facilityName;
	
private String icdValues;
	
	private String cptValues;
	private Integer charges;

	private List<PatientServiceICDCodes> icdCodes;

	private List<PatientServiceCPTCodes> cptCodes;

	private Provider provider;
	
	private PatientDetail patientDetail;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public Date getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(Date dateOfService) {
		this.dateOfService = dateOfService;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCharges() {
		return charges;
	}

	public void setCharges(Integer charges) {
		this.charges = charges;
	}

	public List<PatientServiceICDCodes> getIcdCodes() {
		return icdCodes;
	}

	public void setIcdCodes(List<PatientServiceICDCodes> icdCodes) {
		this.icdCodes = icdCodes;
	}

	public List<PatientServiceCPTCodes> getCptCodes() {
		return cptCodes;
	}

	public void setCptCodes(List<PatientServiceCPTCodes> cptCodes) {
		this.cptCodes = cptCodes;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public PatientDetail getPatientDetail() {
		return patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getIcdValues() {
		return icdValues;
	}

	public void setIcdValues(String icdValues) {
		this.icdValues = icdValues;
	}

	public String getCptValues() {
		return cptValues;
	}

	public void setCptValues(String cptValues) {
		this.cptValues = cptValues;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
