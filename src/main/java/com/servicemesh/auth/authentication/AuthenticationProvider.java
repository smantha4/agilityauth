package com.servicemesh.auth.authentication;

public interface AuthenticationProvider {

	boolean authenticate(LoginContext login);

}
