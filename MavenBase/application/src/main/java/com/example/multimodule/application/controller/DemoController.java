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
import com.example.mail.MailService;

/**
 * The Class DemoController.
 */
@RestController
public class DemoController extends BaseController {
	
	/** The users service. */
	@Autowired
	private UsersService usersService;
	
	/** The Mail service. */
	@Autowired
	private MailService mailService;

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
	 * Test.
	 *
	 * @return the string
	 */
	@GetMapping("/test/adduser")
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
	 * Mailtest.
	 *
	 * @return the string
	 */
	@GetMapping("/test/mail")
	public String mailtest() {
		return mailService.SendMail();
	}

	/**
	 * Gets the all user.
	 *
	 * @return the string
	 */
	@GetMapping("/user")
	public List<Users> getAllUser() {
		List<Users> users = usersService.getAllUser();
		users.forEach((e) -> {
			e.setRoles(null);
		});
		return users;
	}

}
