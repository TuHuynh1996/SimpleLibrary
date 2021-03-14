package com.example.library.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Users;
import com.example.library.repository.UserRepository;
import com.example.library.service.UsersService;

/**
 * The Class UsersServiceImp.
 */
@Service
public class UsersServiceImp implements UsersService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;


	/* (non-Javadoc)
	 * @see com.example.library.service.UsersService#getAllUser()
	 */
	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}

}
