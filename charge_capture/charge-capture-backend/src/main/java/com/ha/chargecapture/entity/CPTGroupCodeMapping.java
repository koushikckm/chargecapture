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
@Table(name = "cptgroupcodemapping")
public class CPTGroupCodeMapping implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6511255424137815130L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "cpt_group_record_id")
	private int cptGroupRecordId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cptcode", referencedColumnName = "cptcode")
	private CPTCodes cptCodes;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getCptGroupRecordId() {
		return cptGroupRecordId;
	}

	public void setCptGroupRecordId(int cptGroupRecordId) {
		this.cptGroupRecordId = cptGroupRecordId;
	}

	public CPTCodes getCptCodes() {
		return cptCodes;
	}

	public void setCptCodes(CPTCodes cptCodes) {
		this.cptCodes = cptCodes;
	}

}
