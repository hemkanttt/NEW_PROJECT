package com.app.service;

import java.util.List;

import com.app.dto.CartDto;
import com.app.dto.UserDto;

public interface UserService {

	boolean checkEmail(String email);
	
	public UserDto createUser(UserDto userDto);
	
	public UserDto getUserByEmailAndPassword(UserDto uDto);
	
	public UserDto getUserById(Integer id);
	
	public UserDto updateUser(UserDto userDto,Integer id);
	
	public UserDto signInWithUserReturnJwt(UserDto userDto);
	
	public List<UserDto> getAllUser();
	
	
	
	
}
