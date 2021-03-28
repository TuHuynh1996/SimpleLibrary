package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.entity.Users;

/**
 * The Interface UsersService.
 */
@Service
public interface UsersService {

	/**
	 * Gets the all user.
	 *
	 * @return the all user
	 */
	public List<Users> getAllUser();

	/**
	 * Adds the user.
	 *
	 * @param entity the entity
	 * @return the list
	 */
	public void addUser(Users entity);

}
