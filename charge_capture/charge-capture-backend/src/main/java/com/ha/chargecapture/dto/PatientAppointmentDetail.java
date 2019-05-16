package com.ha.chargecapture.dto;

import com.ha.chargecapture.entity.PatientDetail;

public class PatientAppointmentDetail {
	
	private AppointmentDetailDTO appointmentDetailDTO;
	
	private PatientDetail patientDetail;

	public AppointmentDetailDTO getAppointmentDetailDTO() {
		return appointmentDetailDTO;
	}

	public void setAppointmentDetailDTO(AppointmentDetailDTO appointmentDetailDTO) {
		this.appointmentDetailDTO = appointmentDetailDTO;
	}

	public PatientDetail getPatientDetail() {
		return patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}
	
	

}
