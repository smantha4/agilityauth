package com.servicemesh.auth.authentication;

public class Credentials {

	public Credentials(String password) {
		this.password = password;
	}

	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}
