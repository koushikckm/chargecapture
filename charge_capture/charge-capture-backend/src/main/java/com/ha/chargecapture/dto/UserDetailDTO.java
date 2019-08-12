
package com.ha.chargecapture.dto;

 public class UserDetailDTO {

 	private int userId;

 	private String userName;

 	private String firstName;

 	private String lastName;

 	private String emailId;

 	//private boolean status;

 	//private int facilityId;

 	public String getUserName() {
		return userName;
	}

 	public void setUserName(String userName) {
		this.userName = userName;
	}

 	public String getFirstName() {
		return firstName;
	}

 	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

 	public String getLastName() {
		return lastName;
	}

 	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

 	public String getEmailId() {
		return emailId;
	}

 	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

 	public int getUserId() {
		return userId;
	}

 	public void setUserId(int userId) {
		this.userId = userId;
	}

 	/*public boolean isStatus() {
		return status;
	}
 	public void setStatus(boolean status) {
		this.status = status;
	}*/
 }

