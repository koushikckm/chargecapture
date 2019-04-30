package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

}
