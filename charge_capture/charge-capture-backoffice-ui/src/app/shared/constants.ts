const BASE_URL="https://localhost:8553";
export const Constants = Object.freeze({    
    GET_ALL_PATIENT_DETAILS:BASE_URL+'/chargecapture/getPatientDetail',
    APPROVE_PATIENT_DETAILS:BASE_URL+'/chargecapture/approvePatientService',
    UPDATE_PATIENT_DETAILS:BASE_URL+'/chargecapture/updatePatientDetail',
    GET_ALL_FACILITY:BASE_URL+'/chargecapture/getFacilityDetail',
    GET_PATIENT_DATA:BASE_URL+'/chargecapture/getPatientDetailById'
});