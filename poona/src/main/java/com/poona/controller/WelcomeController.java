package com.poona.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {
	@RequestMapping("/index.html")
	public String toAddBackLetter(HttpServletRequest request) {
		return "index";
	}
}
