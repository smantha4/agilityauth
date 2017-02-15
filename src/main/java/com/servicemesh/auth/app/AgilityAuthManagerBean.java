package com.servicemesh.auth.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.servicemesh.auth.service.UserService;

@Component
public class AgilityAuthManagerBean implements AuthenticationManager {

	private static final Logger logger = LoggerFactory.getLogger(AgilityAuthManagerBean.class);

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Validate.notNull(authentication.getPrincipal());
		Validate.notNull(authentication.getCredentials());

		logger.info("Authenticating user %s ", authentication.getPrincipal());

		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		if (userService.authenticateUser(username, password)) {

			// Return a dummy authority
			SimpleGrantedAuthority s1 = new SimpleGrantedAuthority("AGILITY");
			List<GrantedAuthority> grantedAuthories = new ArrayList<>();
			grantedAuthories.add(s1);

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					authentication.getPrincipal(), authentication.getCredentials(), grantedAuthories);

			return usernamePasswordAuthenticationToken;
		} else {
			throw new BadCredentialsException("Bad Credentials");
		}

	}

}
