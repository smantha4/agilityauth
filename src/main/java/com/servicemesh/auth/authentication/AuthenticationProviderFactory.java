package com.servicemesh.auth.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderFactory {

	@Autowired
	private PasswordAuthenticationPriovider passwordAuthenticationPriovider;

	public AuthenticationProvider getProvider() {
		return passwordAuthenticationPriovider;
	}

}
