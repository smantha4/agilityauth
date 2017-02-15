package com.servicemesh.auth.authentication;

import com.servicemesh.auth.entity.User;

public class LoginContext {

	private User user;
	private Credentials credentials;

	public Credentials getCredentials() {
		return credentials;
	}

	public User getUser() {
		return user;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
