package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "icdcodes")
public class ICDCodes implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4547808337436162177L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "icdcode")
	private String icdCode;

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

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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
