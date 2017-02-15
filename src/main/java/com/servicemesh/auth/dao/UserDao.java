package com.servicemesh.auth.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.servicemesh.auth.entity.User;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Find users by username
	 * 
	 * @param username
	 * @return
	 */
	public Optional<User> findUserByUserName(String username) {
		Query q = entityManager.createQuery("from User u where u.name=:name").setParameter("name", username);
		List<User> user = q.getResultList();

		if (user != null) {
			return user.stream().findFirst();
		} else {
			return Optional.empty();
		}

	}

}
