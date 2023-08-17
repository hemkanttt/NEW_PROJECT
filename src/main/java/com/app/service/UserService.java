package com.app.service;

import java.util.List;

import com.app.dto.CartDto;
import com.app.dto.UserDto;
import com.app.entities.User;

public interface UserService {

	boolean checkEmail(String email);
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto getUserByEmailAndPassword(UserDto uDto);
	
	public UserDto getUserById(Integer id);
	
	public User updateUser(User u);
	
	public UserDto signInWithUserReturnJwt(UserDto userDto);
	
	public List<UserDto> getAllUser();
	
	
	
	
}
