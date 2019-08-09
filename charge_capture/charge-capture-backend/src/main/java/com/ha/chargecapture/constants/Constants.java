package com.ha.chargecapture.constants;

public class Constants {
	public static final String GET_PATIENTS_FOR_FACILITY_QUERY="SELECT patient_id AS patientId,first_name AS firstName,last_name AS lastName,middle_name AS middleName,name_suffix AS nameSuffix,date_of_birth AS dateOfBirth,gender AS gender,"
			+ "address_line_1 AS address1,address_line_2 AS address2,city AS city,state AS state,zip AS zip,home_phone AS homePhone,mobile_phone AS mobilePhone,email AS email,work_phone AS workPhone,ssn AS ssn "
			+ "FROM patientdetail where facility_id = :facilityId";
	
	public static final String GET_PATIENT_DETAILS_LIST_BY_SEARCH_QUERY = "select psd.service_id as serviceId,psd.patient_id as patientId,concat(pd.first_name,' ',pd.middle_name,' ',pd.last_name) as patientName,pd.date_of_birth as dateOfBirth,"
			+ "psd.date_of_service as dateOfService,psd.status as status,GROUP_CONCAT(icdcode) as icdValues,concat(p.first_name,\" \",p.last_name) as providerName\r\n"
			+ ",fac.name as facilityName,GROUP_CONCAT(cptcode) as cptValues "
			+ "from patientservicedetail psd join patientserviceicdcodes icd on psd.service_id=icd.service_id "
			+ "join patientservicecptcodes cpt on psd.service_id=cpt.service_id  join patientdetail pd on pd.patient_id=psd.patient_id join provider p on p.provider_id=psd.provider_id join facility fac on fac.facility_id=pd.facility_id ";
	
	public static final String INSERT_TO_PATIENT_SERVICE_ICD_CODE="insert into patientserviceicdcodes (service_id,icdcode) values (:serviceId,:icdCode)";
	
	public static final String INSERT_TO_PATIENT_SERVICE_CPD_CODE="insert into patientservicecptcodes (service_id,cptcode) values (:serviceId,:cptCode)";
	
	public static final String GET_FAVORITE_ICDS_FOR_PROVIDER="SELECT picd.icdcode FROM patientserviceicdcodes picd JOIN patientservicedetail psd ON picd.service_id=psd.service_id and psd.provider_id=:providerId ";
	
	public static final String GET_FAVORITE_CPTS_FOR_PROVIDER="SELECT pcpt.cptcode FROM patientservicecptcodes pcpt JOIN patientservicedetail psd ON pcpt.service_id=psd.service_id and psd.provider_id=:providerId ";
	
	private Constants() {
		
	}
}
