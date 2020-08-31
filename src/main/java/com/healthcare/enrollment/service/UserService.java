package com.healthcare.enrollment.service;


import java.util.List;

import com.healthcare.enrollment.model.User;

public interface UserService {
	
	User findById(Long id);

	User findByUserName(String name);

	void saveUser(User User);

	void updateUser(User User);

	void deleteUserById(Long id);

	void deleteAllUsers();

	List<User> findAllUsers();

	boolean isUserExist(User User);
}