package com.poona.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}
	@RequestMapping("/about_poona.html")
	public String aboutPoona() {
		return "about_poona";
	}
	
	@RequestMapping("/class_schedule.html")
	public String classSchedule() {
		return "class_schedule";
	}
	
	@RequestMapping("/teachers.html")
	public String teachers() {
		return "teachers";
	}
	
	@RequestMapping("/join.html")
	public String join() {
		return "join_poona";
	}
	
	@RequestMapping("/graceful_poona.html")
	public String gracefulPoona() {
		return "graceful_poona";
	}
	
	@RequestMapping("/learn_in_india.html")
	public String incredibleIndia() {
		return "learn_in_india";
	}
	
	@RequestMapping("/poona_forum.html")
	public String poonaForum() {
		return "poona_forum";
	}
	@RequestMapping("/contact_us.html")
	public String contact() {
		return "contact_us";
	}
	
	
}
