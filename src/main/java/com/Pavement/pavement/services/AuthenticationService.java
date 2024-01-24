package com.Pavement.pavement.services;

import com.Pavement.pavement.dto.JwtAuthenticationResponse;
import com.Pavement.pavement.dto.RefreshTokenRequest;
import com.Pavement.pavement.dto.SignInRequest;
import com.Pavement.pavement.dto.SignUpRequest;
import com.Pavement.pavement.entities.User;

public interface AuthenticationService {
 
	public User signUp(SignUpRequest signUpRequest);
	public User signIn(SignInRequest signInRequest);
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest RefreshTokenRequest);
}
