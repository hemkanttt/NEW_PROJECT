package com.app.service;

import java.util.List;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CartDto;
import com.app.dto.UserDto;
import com.app.entities.User;
import com.app.repo.UserRepo;
@Service
@org.springframework.transaction.annotation.Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		return mapper.map(userRepo.save(mapper.map(userDto, User.class)),UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer id) {
		
		return mapper.map(userRepo.findById(id) ,UserDto.class);
	}
	
	@Override
	public UserDto getUserByEmailAndPassword(UserDto udto) {
		
		return mapper.map(userRepo.findByEmailAndPassword(udto.getEmail(),udto.getPassword()) ,UserDto.class);
	}

	@Override
	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return userRepo.save(u);
	}

	@Override
	public UserDto signInWithUserReturnJwt(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
