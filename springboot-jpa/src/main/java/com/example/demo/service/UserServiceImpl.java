package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	@Transactional
	@Override
	public List<User> list() {
		
		return userDao.findAll();
	}
	
	
}
