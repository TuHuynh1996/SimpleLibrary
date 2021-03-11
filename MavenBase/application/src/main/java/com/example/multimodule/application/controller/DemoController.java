package com.example.multimodule.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.multimodule.service.MyService;

@RestController
public class DemoController {
	@Autowired
	private MyService myService;

	@GetMapping("/")
	public String home() {
		return myService.message();
	}

}
