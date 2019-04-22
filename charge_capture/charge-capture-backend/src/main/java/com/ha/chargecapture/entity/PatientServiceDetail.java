package com.ha.chargecapture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patientservicedetail")
public class PatientServiceDetail {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "mrn")
	private String mrn;

	@Column(name = "date_of_service")
	private String dateOfService;

	@Column(name = "comments")
	private String comments;

	@Column(name = "icdcodes")
	private String icdCodes;

	@Column(name = "cpdcodes")
	private String cpdCodes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMrn() {
		return mrn;
	}

	public void setMrn(String mrn) {
		this.mrn = mrn;
	}

	public String getComments() {
		return comments;
	}

	public String getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIcdCodes() {
		return icdCodes;
	}

	public void setIcdCodes(String icdCodes) {
		this.icdCodes = icdCodes;
	}

	public String getCpdCodes() {
		return cpdCodes;
	}

	public void setCpdCodes(String cpdCodes) {
		this.cpdCodes = cpdCodes;
	}

}
