package com.example.demo.dao1;



import java.util.List;

import com.example.demo.domain.User;

public interface UserDao1  {
	
	void save(User user);

	List<User> list();

}
