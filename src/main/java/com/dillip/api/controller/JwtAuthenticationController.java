package com.dillip.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dillip.api.config.JwtTokenUtil;
import com.dillip.api.dto.UserDTO;
import com.dillip.api.request.JwtRequest;
import com.dillip.api.response.ApiEntity;
import com.dillip.api.response.ApiResponseObject;
import com.dillip.api.response.JwtResponse;
import com.dillip.api.service.JwtUserDetailsService;
import com.dillip.api.util.ProjectConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "1. Authentication Controller")
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Operation(summary = "loginAndGenerateJwtToken")
	@PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseObject> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

		HttpStatus status = HttpStatus.OK;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		JwtResponse response = null;
		try {
			log.info(
					"########## Hitting /login API in JwtAuthentication Controller ########## :: Input RequestBody :: JwtRequest"
							+ new ObjectMapper().writeValueAsString(authenticationRequest));
			authenticate(authenticationRequest.getUserName(), authenticationRequest.getPassword());
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
			final String token = jwtTokenUtil.generateToken(userDetails);
			if (token != null && !token.equals("")) {
				response = new JwtResponse(token);
				message = "Token is Generated";
			} else {
				message = "Token is not Generated";
			}
		} catch (Exception e) {
			message = e.getMessage();
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
			log.info("########## Exception Occured in /login API in JwtAuthenticationController ########## "+e.toString());
		}

		return new ResponseEntity<>(new ApiEntity<JwtResponse>(message, response), httpHeaders, status);
	}

	@Operation(summary = "registerUserInDabatase")
	@PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseObject> saveUser(@RequestBody UserDTO userDto) {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		String response = null;
		try {
			log.info("########## Hitting /register API in JwtAuthenticationController ########## :: Input RequestBody :: "
					+ new ObjectMapper().writeValueAsString(userDto));
			response = userDetailsService.saveUser(userDto);
			if (response.equals(ProjectConstant.SUCCESS_MSG)) {
				message = ProjectConstant.CREATED_MSG;
				status = HttpStatus.CREATED;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in /register API in JwtAuthenticationController ########## "+e.toString());
			message = "Error Occured While Saving the User";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<String>(message, response), httpHeaders, status);
	}
	
	@Operation(summary = "getAllRegisteredUserDetails")
	@GetMapping(path = "/users", produces = "application/json")
	public ResponseEntity<ApiResponseObject> fetchUser() {

		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<UserDTO> response = null;
		try {
			log.info("########## Hitting /users API in JwtAuthenticationController ##########");
			response = userDetailsService.fetchUser();
			if (response != null && !response.isEmpty()) {
				message = "Users Found";
				status = HttpStatus.OK;
			}
			else
			{
				message = "Users not Found";
				status = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			log.info("########## Exception Occured in /users API in JwtAuthenticationController ########## "+e.toString());
			message = "Error Occured While Fetching the User";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(new ApiEntity<List<UserDTO>>(message, response), httpHeaders, status);
	}

	private void authenticate(String userName, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
