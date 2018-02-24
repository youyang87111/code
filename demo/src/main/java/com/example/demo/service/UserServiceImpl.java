package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;

	@Override
	public List<User> list() {
		
		PageHelper.startPage(0, 2, true);
		
		return userDao.list();
	}
	
	
}
