package com.example.demo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	 	
	 	@Autowired
	 	UserService userService;

	    @RequestMapping(value = "/listusers",method = RequestMethod.GET)
	    public  List<User> getUsers(String pageno){
	    	int i = 1/0;
	    	System.out.println(i);
	    	Object o = null;
	    	o.toString();
	       return userService.list();
	    }
	    
	   /* @RequestMapping("/index")
	    public String index(ModelMap map) {
			map.addAttribute("name", "余胜军");
			map.put("sex",1);//0 男 1 女 其他
			List<String> userList=new ArrayList<String>();
			userList.add("张三");
			userList.add("王五");
			userList.add("王麻子");
			map.addAttribute("userList", userList);
			return "index";
		}*/
	    
	    @RequestMapping("/index")
	    public String index(ModelMap map) {
			map.addAttribute("name", "余胜军");
			map.put("sex",1);//0 男 1 女 其他
			List<String> userList=new ArrayList<String>();
			userList.add("张三");
			userList.add("王五");
			userList.add("王麻子");
			map.addAttribute("userList", userList);
			return "index";
		}

}
