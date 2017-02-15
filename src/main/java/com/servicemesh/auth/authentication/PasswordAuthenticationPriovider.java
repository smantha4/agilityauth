package com.servicemesh.auth.authentication;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import com.servicemesh.auth.authentication.PasswordAlgorithm.PasswordAlgorithmEnum;
import com.servicemesh.auth.entity.User;

@Component
public class PasswordAuthenticationPriovider implements AuthenticationProvider {

	public PasswordAuthenticationPriovider() {

	}

	@Override
	public boolean authenticate(LoginContext login) {

		Validate.notNull(login.getCredentials().getPassword());
		Validate.notNull(login.getUser());

		// Get password
		String password = login.getCredentials().getPassword();

		// Get the user Object
		User user = login.getUser();

		PasswordAlgorithm passwordAlgorithm;
		try {
			passwordAlgorithm = new PasswordAlgorithm(PasswordAlgorithmEnum.valueOf(user.getDigestAlgorithm()));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("No such authentication algoritm", e);
		}

		byte[] digestSalt;
		try {
			digestSalt = Hex.decodeHex(user.getDigestSalt().toCharArray());
		} catch (DecoderException e1) {
			throw new RuntimeException("invalid Salt");
		}

		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), digestSalt, user.getDigestIterations(),
				passwordAlgorithm.getLength());

		SecretKeyFactory secretKeyFactory = passwordAlgorithm.getGetKeyFactory();

		try {
			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

			byte[] calculatedEncryptedPassword = secretKey.getEncoded();

			String calculatedPassword = new String(Hex.encodeHex(calculatedEncryptedPassword));

			// Compare the existing digest with calculated password
			return user.getDigest().equals(calculatedPassword);

		} catch (InvalidKeySpecException e) {
			throw new RuntimeException("Invalid Key Spec", e);
		}

	}
}
