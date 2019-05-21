package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cpdcodes")
public class CPDCodes implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5485028637636801793L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "cpdcode")
	private String cpdcode;

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

	public String getCpdcode() {
		return cpdcode;
	}

	public void setCpdcode(String cpdcode) {
		this.cpdcode = cpdcode;
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
