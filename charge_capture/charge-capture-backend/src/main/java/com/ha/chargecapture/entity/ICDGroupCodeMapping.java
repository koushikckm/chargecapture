package com.ha.chargecapture.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "icdgroupcodemapping")
public class ICDGroupCodeMapping implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6511255424137815130L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "icd_group_record_id")
	private int icdGroupRecordId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "icdcode", referencedColumnName = "icdcode")
	private ICDCodes icdCodes;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getIcdGroupRecordId() {
		return icdGroupRecordId;
	}

	public void setIcdGroupRecordId(int icdGroupRecordId) {
		this.icdGroupRecordId = icdGroupRecordId;
	}

	public ICDCodes getIcdCodes() {
		return icdCodes;
	}

	public void setIcdCodes(ICDCodes icdCodes) {
		this.icdCodes = icdCodes;
	}

}
