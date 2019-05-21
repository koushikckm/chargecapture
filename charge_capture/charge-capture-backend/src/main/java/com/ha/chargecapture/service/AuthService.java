package com.ha.chargecapture.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Value("${auth.UserName}")
	private String UserName;

	@Value("${auth.Password}")
	private String Password;

	@Value("${auth.BaseAddress}")
	private String BaseAddress;

	@Value("${auth.TokenEndpoint}")
	private String TokenEndpoint;

	@Value("${auth.ClientID}")
	private String ClientID;

	@Value("${auth.AdapterURL}")
	private String AdapterURL;

	@Value("${auth.AdminURL}")
	private String AdminURL;

	@Value("${auth.APICommunicatorURL}")
	private String APICommunicatorURL;

	@Value("${auth.GECPSDatabaseConnection}")
	private String GECPSDatabaseConnection;

	@Value("${auth.CADatabaseConnection}")
	private String CADatabaseConnection;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getBaseAddress() {
		return BaseAddress;
	}

	public void setBaseAddress(String baseAddress) {
		BaseAddress = baseAddress;
	}

	public String getTokenEndpoint() {
		return TokenEndpoint;
	}

	public void setTokenEndpoint(String tokenEndpoint) {
		TokenEndpoint = tokenEndpoint;
	}

	public String getClientID() {
		return ClientID;
	}

	public void setClientID(String clientID) {
		ClientID = clientID;
	}

	public String getAdapterURL() {
		return AdapterURL;
	}

	public void setAdapterURL(String adapterURL) {
		AdapterURL = adapterURL;
	}

	public String getAdminURL() {
		return AdminURL;
	}

	public void setAdminURL(String adminURL) {
		AdminURL = adminURL;
	}

	public String getAPICommunicatorURL() {
		return APICommunicatorURL;
	}

	public void setAPICommunicatorURL(String aPICommunicatorURL) {
		APICommunicatorURL = aPICommunicatorURL;
	}

	public String getGECPSDatabaseConnection() {
		return GECPSDatabaseConnection;
	}

	public void setGECPSDatabaseConnection(String gECPSDatabaseConnection) {
		GECPSDatabaseConnection = gECPSDatabaseConnection;
	}

	public String getCADatabaseConnection() {
		return CADatabaseConnection;
	}

	public void setCADatabaseConnection(String cADatabaseConnection) {
		CADatabaseConnection = cADatabaseConnection;
	}

}
