package com.ha.chargecapture.dto;

 public class AppointmentRequestDTO {

 	private String startDate;

 	private String endDate;

 	private String location;

 	private String status;

 	private String practitioner;

 	public String getStartDate() {
		return startDate;
	}

 	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

 	public String getEndDate() {
		return endDate;
	}

 	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

 	public String getLocation() {
		return location;
	}

 	public void setLocation(String location) {
		this.location = location;
	}

 	public String getStatus() {
		return status;
	}

 	public void setStatus(String status) {
		this.status = status;
	}

 	public String getPractitioner() {
		return practitioner;
	}

 	public void setPractitioner(String practitioner) {
		this.practitioner = practitioner;
	}

 }