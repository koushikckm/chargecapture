package com.ha.chargecapture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cpdcodes")
public class CPDCodes {

	@Id
	@Column(name = "cpdcode")
	private String cpdcode;

	@Column(name = "description")
	private String description;

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
