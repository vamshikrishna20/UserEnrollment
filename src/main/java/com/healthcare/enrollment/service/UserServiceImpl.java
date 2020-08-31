package com.healthcare.enrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.enrollment.model.User;
import com.healthcare.enrollment.repositories.UserRepository;



@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository UserRepository;

	public User findById(Long id) {
		return UserRepository.findOne(id);
	}

	public User findByUserName(String name) {
		return UserRepository.findByUserName(name);
	}

	public void saveUser(User User) {
		UserRepository.save(User);
	}

	public void updateUser(User User){
		saveUser(User);
	}

	public void deleteUserById(Long id){
		UserRepository.delete(id);
	}

	public void deleteAllUsers(){
		UserRepository.deleteAll();
	}

	public List<User> findAllUsers(){
		return UserRepository.findAll();
	}

	public boolean isUserExist(User User) {
		return findByUserName(User.getUserName()) != null;
	}

}
