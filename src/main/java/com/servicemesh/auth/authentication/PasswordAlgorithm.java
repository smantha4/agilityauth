package com.servicemesh.auth.authentication;

import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKeyFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author srirammantha
 */
public class PasswordAlgorithm {

	/**
	 * Enum for {@link PasswordAlgorithm} Contains the list of algorithms in
	 * order of the strength of the hash
	 *
	 * @author srirammantha
	 */
	public enum PasswordAlgorithmEnum {
		PBKDF2WithHmacSHA1(160), PBKDF2WithHmacSHA224(224), PBKDF2WithHmacSHA256(256), PBKDF2WithHmacSHA384(
				384), PBKDF2WithHmacSHA512(512);

		PasswordAlgorithmEnum(int l) {
			length = l;
		}

		/**
		 * @return the length
		 */
		public int getLength() {
			return length;
		}

		private int length;

		/**
		 * Returns true of the newer one is a better/stronger algorithm
		 * 
		 * @param newAlgorithm
		 * @param oldAlgorithm
		 * @return
		 */
		public static boolean isStrongerAlgorithm(String newAlgorithm, String oldAlgorithm) {
			if (StringUtils.isBlank(oldAlgorithm)) {
				return true;
			}
			if (StringUtils.isBlank(newAlgorithm)) {
				return false;
			}

			PasswordAlgorithmEnum n = PasswordAlgorithmEnum.valueOf(newAlgorithm);
			PasswordAlgorithmEnum o = PasswordAlgorithmEnum.valueOf(oldAlgorithm);

			return n.ordinal() > o.ordinal();
		}
	}

	private PasswordAlgorithmEnum name;
	private SecretKeyFactory keyFactory;

	public PasswordAlgorithm(PasswordAlgorithmEnum name) throws NoSuchAlgorithmException {
		Validate.notNull(name);
		this.name = name;
		keyFactory = SecretKeyFactory.getInstance(name.name());
	}

	/**
	 * @return the name
	 */
	public PasswordAlgorithmEnum getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(PasswordAlgorithmEnum name) {
		this.name = name;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return name.getLength();
	}

	/**
	 * @return the getKeyFactory
	 */
	public SecretKeyFactory getGetKeyFactory() {
		return keyFactory;
	}

	/**
	 * @param getKeyFactory
	 *            the getKeyFactory to set
	 */
	public void setGetKeyFactory(SecretKeyFactory getKeyFactory) {
		keyFactory = getKeyFactory;
	}

}
