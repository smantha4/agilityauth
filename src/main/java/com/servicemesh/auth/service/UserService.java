package com.servicemesh.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicemesh.auth.authentication.AuthenticationProvider;
import com.servicemesh.auth.authentication.AuthenticationProviderFactory;
import com.servicemesh.auth.authentication.Credentials;
import com.servicemesh.auth.authentication.LoginContext;
import com.servicemesh.auth.dao.UserDao;
import com.servicemesh.auth.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationProviderFactory authenticationProviderFactory;

	@Transactional
	public boolean authenticateUser(String username, String password) {

		Optional<User> user = userDao.findUserByUserName(username);

		if (user.isPresent()) {
			AuthenticationProvider authenticationProvider = authenticationProviderFactory.getProvider();

			LoginContext context = new LoginContext();
			context.setUser(user.get());
			context.setCredentials(new Credentials(password));

			return authenticationProvider.authenticate(context);
		} else {
			return false;
		}

	}

}
