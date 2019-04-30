package com.ha.chargecapture.dao;

import java.util.List;

public class PatientServiceDetailDTO {

	private String patientId;

	private String dateOfService;

	private String comments;

	private String status;

	private int charges;

	private List<Integer> icdRecordIds;

	private List<Integer> cpdRecordIds;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

	public List<Integer> getIcdRecordIds() {
		return icdRecordIds;
	}

	public void setIcdRecordIds(List<Integer> icdRecordIds) {
		this.icdRecordIds = icdRecordIds;
	}

	public List<Integer> getCpdRecordIds() {
		return cpdRecordIds;
	}

	public void setCpdRecordIds(List<Integer> cpdRecordIds) {
		this.cpdRecordIds = cpdRecordIds;
	}

}
