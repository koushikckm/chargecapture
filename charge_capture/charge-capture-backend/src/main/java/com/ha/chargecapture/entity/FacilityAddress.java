package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facilityaddress")
public class FacilityAddress implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8036143244814211637L;

	@Id
	@Column(name = "address_id")
	private int addressId;

	@Column(name = "address_street1")
	private String addressStreet1;

	@Column(name = "address_street2")
	private String addressStreet2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zipcode")
	private String zipcode;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressStreet1() {
		return addressStreet1;
	}

	public void setAddressStreet1(String addressStreet1) {
		this.addressStreet1 = addressStreet1;
	}

	public String getAddressStreet2() {
		return addressStreet2;
	}

	public void setAddressStreet2(String addressStreet2) {
		this.addressStreet2 = addressStreet2;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
