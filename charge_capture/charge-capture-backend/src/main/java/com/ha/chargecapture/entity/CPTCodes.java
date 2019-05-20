package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cptcodes")
public class CPTCodes implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5485028637636801793L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "cptcode")
	private String cptcode;

	@Column(name = "description")
	private String description;

	@Transient
	private boolean isFavouriteForProvider;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getCptcode() {
		return cptcode;
	}

	public void setCptcode(String cptcode) {
		this.cptcode = cptcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFavouriteForProvider() {
		return isFavouriteForProvider;
	}

	public void setFavouriteForProvider(boolean isFavouriteForProvider) {
		this.isFavouriteForProvider = isFavouriteForProvider;
	}

}
