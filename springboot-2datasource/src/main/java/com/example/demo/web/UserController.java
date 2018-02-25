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

	    @RequestMapping(value = "/list1",method = RequestMethod.GET)
	    public  List<User> getUsers1(){
	    	List<User> list = null;
	    	try {
	    		list = userService.list1();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	        return list;
	    }
	    
	    @RequestMapping(value = "/list2",method = RequestMethod.GET)
	    public  List<User> getUsers2(){
	       return userService.list2();
	    }
	    
	    @RequestMapping(value = "/save",method = RequestMethod.GET)
	    public String save() {
	    	
	    	User user = new User();
	    	user.setId(20);
	    	user.setName("youyang");
	    	user.setMoney(100);
	    	try {
	    		userService.save(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    	
	    	return "success";
	    	
	    }
}
