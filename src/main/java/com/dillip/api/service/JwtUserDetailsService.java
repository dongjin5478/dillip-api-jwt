package com.dillip.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dillip.api.dto.UserDTO;
import com.dillip.api.entity.UserEntity;
import com.dillip.api.repository.UserRepository;
import com.dillip.api.util.ProjectConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.getUserName(userName);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found with User Name: " + userName);
		}
		return new User(userEntity.getUserName(), userEntity.getPassword(), new ArrayList<>());
	}

	public String saveUser(UserDTO userDto) {
		String message = null;
		try {
			UserEntity newUser = new UserEntity();
			newUser.setUserName(userDto.getUserName());
			newUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
			newUser.setEmailId(userDto.getEmailId());
			userRepository.save(newUser);
			message = ProjectConstant.SUCCESS_MSG;
		} catch (Exception e) {
			log.info("########## Exception Occured in saveUser() method in JwtUserDetailsService ########## "+e.toString());
			message = ProjectConstant.ERR_MSG;
		}
		return message;
	}
	
	public List<UserDTO> fetchUser()
	{
		List<UserEntity> userEntityList = null;
		List<UserDTO> userListDto = new ArrayList<>();
		try {
			userEntityList = userRepository.findAll();
			for(UserEntity userEntity : userEntityList)
			{
				UserDTO userDto = new UserDTO();
				userDto.setUserName(userEntity.getUserName());
				userDto.setEmailId(userEntity.getEmailId());
				userDto.setPassword(userEntity.getPassword());
				userListDto.add(userDto);
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in fetchUser() method in JwtUserDetailsService ########## "+e.toString());
		}
		return userListDto;
	}

}
