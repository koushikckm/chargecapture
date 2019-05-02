package com.ha.chargecapture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patientdetail")
public class PatientDetail implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -750969060563002130L;

	@Id
	@Column(name = "patient_id")
	private String patientId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "name_suffix")
	private String nameSuffix;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address_line_1")
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private String zip;

	@Column(name = "home_phone")
	private String homePhone;

	@Column(name = "mobile_phone")
	private String mobilePhone;

	@Column(name = "email")
	private String email;

	@Column(name = "work_phone")
	private String workPhone;

	@Column(name = "primary_language")
	private String primaryLanguage;

	@Column(name = "chart_number")
	private String chartNumber;

	@Column(name = "ssn")
	private String ssn;

	@Column(name = "is_processed")
	private Integer isProcessed;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = PatientServiceDetail.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
	private List<PatientServiceDetail> patientServiceDetail;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = PatientReferringProvider.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id", referencedColumnName = "patient_id")
	private List<PatientReferringProvider> referringProviders;

	@ManyToOne(optional = false)
	@JoinColumn(name = "facility_id")
	private Facility facility;

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getPrimaryLanguage() {
		return primaryLanguage;
	}

	public void setPrimaryLanguage(String primaryLanguage) {
		this.primaryLanguage = primaryLanguage;
	}

	public String getChartNumber() {
		return chartNumber;
	}

	public void setChartNumber(String chartNumber) {
		this.chartNumber = chartNumber;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Integer getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(Integer isProcessed) {
		this.isProcessed = isProcessed;
	}

	public List<PatientServiceDetail> getPatientServiceDetail() {
		return patientServiceDetail;
	}

	public void setPatientServiceDetail(List<PatientServiceDetail> patientServiceDetail) {
		this.patientServiceDetail = patientServiceDetail;
	}

	public List<PatientReferringProvider> getReferringProviders() {
		return referringProviders;
	}

	public void setReferringProviders(List<PatientReferringProvider> referringProviders) {
		this.referringProviders = referringProviders;
	}

}
