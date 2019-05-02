package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facility")
public class Facility implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5323682504787904306L;

	@Id
	@Column(name = "facility_id")
	private int facilityId;

	@Column(name = "clia_number")
	private String cliaNumber;

	@Column(name = "name")
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "address_id")
	private FacilityAddress facilityAddress;

	@Column(name = "pos_id")
	private int posId;

	@Column(name = "phone1")
	private String phone1;

	@Column(name = "phone2")
	private String phone2;

	@Column(name = "fax")
	private String fax;

	@Column(name = "tax_id")
	private String taxId;

	@Column(name = "provider_rep_id")
	private int providerRepId;

	@Column(name = "is_deleted")
	private int isDeleted;

	@Column(name = "facility_npi")
	private String facilityNpi;

	@Column(name = "group_npi")
	private String groupNpi;

	@Column(name = "is_emergency")
	private int isEmergency;

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getCliaNumber() {
		return cliaNumber;
	}

	public void setCliaNumber(String cliaNumber) {
		this.cliaNumber = cliaNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FacilityAddress getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(FacilityAddress facilityAddress) {
		this.facilityAddress = facilityAddress;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public int getProviderRepId() {
		return providerRepId;
	}

	public void setProviderRepId(int providerRepId) {
		this.providerRepId = providerRepId;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getFacilityNpi() {
		return facilityNpi;
	}

	public void setFacilityNpi(String facilityNpi) {
		this.facilityNpi = facilityNpi;
	}

	public String getGroupNpi() {
		return groupNpi;
	}

	public void setGroupNpi(String groupNpi) {
		this.groupNpi = groupNpi;
	}

	public int getIsEmergency() {
		return isEmergency;
	}

	public void setIsEmergency(int isEmergency) {
		this.isEmergency = isEmergency;
	}

}
