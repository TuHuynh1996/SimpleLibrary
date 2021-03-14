package com.example.multimodule.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.controller.BaseController;
import com.example.library.entity.Users;
import com.example.library.service.UsersService;

/**
 * The Class DemoController.
 */
@RestController
public class DemoController extends BaseController {

//	/** The my service. */
	@Autowired
	private UsersService user;

	/**
	 * Home.
	 *
	 * @return the string
	 */
	@GetMapping("/")
	public String home() {
//		return myService.message();
		return "";
	}

	/**
	 * Test 1.
	 *
	 * @return the string
	 */
	@GetMapping("/test1")
	public List<Users> test1() {
		return user.getAllUser();
	}

}
