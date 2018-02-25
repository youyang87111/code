package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao1.UserDao1;
import com.example.demo.dao2.UserDao2;
import com.example.demo.domain.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao1 userDao1;
	@Autowired
	UserDao2 userDao2;
	
	@Override
	@Transactional
	public void save(User user) {
		
		userDao1.save(user);
		userDao2.save(user);
		int i = 1/0;
	}

	@Transactional
	@Override
	public List<User> list1() {
		
		return userDao1.list();
	}
	
	@Transactional
	@Override
	public List<User> list2() {
		
		return userDao2.list();
	}


	
	
}
