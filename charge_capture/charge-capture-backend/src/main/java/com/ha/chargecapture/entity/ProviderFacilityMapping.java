package com.ha.chargecapture.entity;

 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

 @Entity
@Table(name="providerfacilitymapping")
public class ProviderFacilityMapping {

 	@Id
	@Column(name="record_id")
	private int id;

 	@Column(name="provider_id")
	private int userId;

 	@Column(name="facility_id")
	private int facilityId;

 	public int getId() {
		return id;
	}

 	public void setId(int id) {
		this.id = id;
	}

 	public int getUserId() {
		return userId;
	}

 	public void setUserId(int userId) {
		this.userId = userId;
	}

 	public int getFacilityId() {
		return facilityId;
	}

 	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

 }