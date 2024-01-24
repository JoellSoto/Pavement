package com.Pavement.pavement.services.impl;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Pavement.pavement.dto.JwtAuthenticationResponse;
import com.Pavement.pavement.dto.RefreshTokenRequest;
import com.Pavement.pavement.dto.SignInRequest;
import com.Pavement.pavement.dto.SignUpRequest;
import com.Pavement.pavement.entities.Roles;
import com.Pavement.pavement.entities.User;
import com.Pavement.pavement.exceptions.ResourceNotFoundException;
import com.Pavement.pavement.repositories.RoleRepository;
import com.Pavement.pavement.repositories.UserRepository;
import com.Pavement.pavement.services.AuthenticationService;
import com.Pavement.pavement.services.JWTService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;
	private final RoleRepository roleRepository;
	
	@Autowired
	public AuthenticationServiceImpl(UserRepository userRepository,AuthenticationManager authenticationManager, JWTService jwtService, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.roleRepository = roleRepository;
	}
	
	
	public User signUp(SignUpRequest signUpRequest) {
		Roles role = new Roles();
		User user = new User();
		role=roleRepository.findByName("USER").get();

		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setSecondName(signUpRequest.getLastName());
		user.setRoles(Collections.singletonList(role));
		user.setPassword(signUpRequest.getPassword());
		
		return userRepository.save(user);
	}
	
	
	public User signIn(SignInRequest signInRequest) {
		var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
		var acessToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

		try{
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
		} catch (Exception e) {
			throw new ResourceNotFoundException("System Login",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "Incorrect Username/Password!");
		}
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(acessToken);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		user.setJwtToken(jwtAuthenticationResponse);
		
		return user;
	}
	
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest RefreshTokenRequest) {
		String userEmail = jwtService.extractUserName(RefreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		
		if(jwtService.isTokenValid(RefreshTokenRequest.getToken(), user)) {
			var acessToken= jwtService.generateToken(user);
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setToken(acessToken);
			jwtAuthenticationResponse.setRefreshToken(RefreshTokenRequest.getToken());
			return jwtAuthenticationResponse;
		}
		return null;
	}
	
}
