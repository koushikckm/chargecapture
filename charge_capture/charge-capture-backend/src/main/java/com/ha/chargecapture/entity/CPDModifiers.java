package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cpdmodifiers")
public class CPDModifiers implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7285142892901738074L;

	@Id
	@Column(name = "modifier_code")
	private String modifierCode;

	@Column(name = "description")
	private String description;

	public String getModifierCode() {
		return modifierCode;
	}

	public void setModifierCode(String modifierCode) {
		this.modifierCode = modifierCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
