package com.ha.chargecapture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "icdgroup")
public class ICDGroup implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4759945120471274018L;

	@Id
	@Column(name = "record_id")
	private int recordId;

	@Column(name = "groupname")
	private String groupName;

	@OneToMany(targetEntity = ICDGroupCodeMapping.class, mappedBy = "icdGroupRecordId")
	// @OneToMany(fetch = FetchType.LAZY, targetEntity = ICDGroupCodeMapping.class,
	// cascade = CascadeType.ALL)
	// @JoinColumn(name = "icd_group_record_id", referencedColumnName =
	// "icd_group_record_id")
	private List<ICDGroupCodeMapping> icdGroupCodes;

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

	public List<ICDGroupCodeMapping> getIcdGroupCodes() {
		return icdGroupCodes;
	}

	public void setIcdGroupCodes(List<ICDGroupCodeMapping> icdGroupCodes) {
		this.icdGroupCodes = icdGroupCodes;
	}

}
