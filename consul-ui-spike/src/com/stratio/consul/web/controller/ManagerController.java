package com.stratio.consul.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ManagerController {
	
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public ModelAndView helloWorld(){
 
		ModelAndView model = new ModelAndView("HelloWorldPage");
		model.addObject("msg", "hello world");
 
		return model;
	}
}
