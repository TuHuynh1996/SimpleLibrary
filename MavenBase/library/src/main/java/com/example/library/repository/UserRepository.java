package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Users;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	/**
	 * Find by name.
	 *
	 * @param username the username
	 * @return the users
	 */
	public Users findByName(String username);

}
