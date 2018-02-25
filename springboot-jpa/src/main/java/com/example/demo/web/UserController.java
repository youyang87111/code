package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	 	
	 	@Autowired
	 	UserService userService;

	    @RequestMapping(value = "/list",method = RequestMethod.GET)
	    public  List<User> getUsers(){
	       return userService.list();
	    }
}
