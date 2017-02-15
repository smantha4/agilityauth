/**
 * COPYRIGHT (C) 2008-2012 SERVICEMESH, INC.  ALL RIGHTS RESERVED.  CONFIDENTIAL AND PROPRIETARY.
 *
 * ALL SOFTWARE, INFORMATION AND ANY OTHER RELATED COMMUNICATIONS (COLLECTIVELY, "WORKS") ARE CONFIDENTIAL AND PROPRIETARY INFORMATION THAT ARE THE EXCLUSIVE PROPERTY OF SERVICEMESH.     ALL WORKS ARE PROVIDED UNDER THE APPLICABLE AGREEMENT OR END USER LICENSE AGREEMENT IN EFFECT BETWEEN YOU AND SERVICEMESH.  UNLESS OTHERWISE SPECIFIED IN THE APPLICABLE AGREEMENT, ALL WORKS ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A PARTICULAR PURPOSE.  ALL USE, DISCLOSURE AND/OR REPRODUCTION OF WORKS NOT EXPRESSLY AUTHORIZED BY SERVICEMESH IS STRICTLY PROHIBITED.
 *
 */

package com.servicemesh.auth.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;

@Entity
@Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "VMUser")
public class User {

	final static Logger logger = Logger.getLogger(User.class);

	private int id;
	private String digest;
	private String name;
	private String digestAlgorithm;
	private Calendar digestExpiration;
	private Integer digestIterations;
	private String digestSalt;
	private String email;
	private boolean enabled;
	private int failedLoginAttempts;
	private Calendar lastFailedLoginAttempt;
	private String lastFailedLoginAddress;
	private boolean isExternalUser = false;

	public User() {
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	public void setDigestAlgorithm(String digestAlgorithm) {
		this.digestAlgorithm = digestAlgorithm;
	}

	public Calendar getDigestExpiration() {
		return digestExpiration;
	}

	public void setDigestExpiration(Calendar digestExpiration) {
		this.digestExpiration = digestExpiration;
	}

	public Integer getDigestIterations() {
		return digestIterations;
	}

	public void setDigestIterations(Integer digestIterations) {
		this.digestIterations = digestIterations;
	}

	public String getDigestSalt() {
		return digestSalt;
	}

	public void setDigestSalt(String digestSalt) {
		this.digestSalt = digestSalt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public Calendar getLastFailedLoginAttempt() {
		return lastFailedLoginAttempt;
	}

	public void setLastFailedLoginAttempt(Calendar lastFailedLoginAttempt) {
		this.lastFailedLoginAttempt = lastFailedLoginAttempt;
	}

	public String getLastFailedLoginAddress() {
		return lastFailedLoginAddress;
	}

	public void setLastFailedLoginAddress(String lastFailedLoginAddress) {
		this.lastFailedLoginAddress = lastFailedLoginAddress;
	}

	public boolean isExternalUser() {
		return isExternalUser;
	}

	public void setExternalUser(boolean isExternalUser) {
		this.isExternalUser = isExternalUser;
	}

}
