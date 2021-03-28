package com.example.multimodule.application.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.controller.BaseController;
import com.example.library.entity.Role;
import com.example.library.entity.Users;
import com.example.library.service.UsersService;

/**
 * The Class DemoController.
 */
@RestController
public class DemoController extends BaseController {
	/** The my service. */
	@Autowired
	private UsersService usersService;

	/** The password encoder. */
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * Home.
	 *
	 * @return the string
	 */
	@GetMapping("/")
	public String home() {
		return "";
	}

	/**
	 * Home.
	 *
	 * @return the string
	 */
	@GetMapping("/test")
	public String test() {
		Role role = new Role();
		role.setId(1);
		role.setName("User");
		Users user = new Users();
		user.setName("tuhuynh");
		user.setPassword(passwordEncoder.encode("12345"));
		Set<Role> listRule = new HashSet<Role>(Arrays.asList(new Role[] { role }));
		user.setRoles(listRule);
		usersService.addUser(user);
		return "test";
	}

	/**
	 * Test 1.
	 *
	 * @return the string
	 */
	@GetMapping("/user")
	public List<Users> test1() {
		List<Users> users = usersService.getAllUser();
		users.forEach((e) -> {
			e.setRoles(null);
		});
		return users;
	}

}
