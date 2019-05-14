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
@Table(name = "cpdgroupcodemapping")
public class CPDGroupCodeMapping implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6511255424137815130L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "cpd_group_record_id")
	private int cpdGroupRecordId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cpdcode", referencedColumnName = "cpdcode")
	private CPDCodes cpdCodes;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getCpdGroupRecordId() {
		return cpdGroupRecordId;
	}

	public void setCpdGroupRecordId(int cpdGroupRecordId) {
		this.cpdGroupRecordId = cpdGroupRecordId;
	}

	public CPDCodes getCpdCodes() {
		return cpdCodes;
	}

	public void setCpdCodes(CPDCodes cpdCodes) {
		this.cpdCodes = cpdCodes;
	}

}
