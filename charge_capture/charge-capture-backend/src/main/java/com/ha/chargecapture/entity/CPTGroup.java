package com.ha.chargecapture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cptgroup")
public class CPTGroup implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4759945120471274018L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "groupname")
	private String groupName;

	@OneToMany(targetEntity = CPTGroupCodeMapping.class, mappedBy = "cptGroupRecordId")
	private List<CPTGroupCodeMapping> cptGroupCodes;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<CPTGroupCodeMapping> getCptGroupCodes() {
		return cptGroupCodes;
	}

	public void setCptGroupCodes(List<CPTGroupCodeMapping> cptGroupCodes) {
		this.cptGroupCodes = cptGroupCodes;
	}

}
