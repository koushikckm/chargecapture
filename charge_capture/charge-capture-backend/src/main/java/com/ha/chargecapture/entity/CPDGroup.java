package com.ha.chargecapture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cpdgroup")
public class CPDGroup implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4759945120471274018L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "groupname")
	private String groupName;

	@OneToMany(targetEntity = CPDGroupCodeMapping.class, mappedBy = "cpdGroupRecordId")
	private List<CPDGroupCodeMapping> cpdGroupCodes;

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

	public List<CPDGroupCodeMapping> getCpdGroupCodes() {
		return cpdGroupCodes;
	}

	public void setCpdGroupCodes(List<CPDGroupCodeMapping> cpdGroupCodes) {
		this.cpdGroupCodes = cpdGroupCodes;
	}

}
