package com.example.multimodule.application.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.library.entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class JwtAuthenticationFilter.
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	/** The authentication manager. */
	private AuthenticationManager authenticationManager;
	
	/** The secret key. */
	@Value("${web.module.jwtproperties.secret}")
	private String secretKey;

	/**
	 * Instantiates a new jwt authentication filter.
	 *
	 * @param authenticationManager the authentication manager
	 */
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http
	 * .HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/*
	 * Trigger when we issue POST request to /login We also need to pass in
	 * {"username":"dan", "password":"dan123"} in the request body
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		// Grab credentials and map them to login viewmodel
		Users credentials = null;
		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), Users.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create login token
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				credentials.getName(), credentials.getPassword(), new ArrayList<>());

		// Authenticate user
		Authentication auth = authenticationManager.authenticate(authenticationToken);

		return auth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AbstractAuthenticationProcessingFilter#successfulAuthentication(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * javax.servlet.FilterChain, org.springframework.security.core.Authentication)
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Grab principal
		UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

		// Create JWT Token
		String token = JWT.create().withSubject(principal.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
				.sign(Algorithm.HMAC512(secretKey.getBytes()));

		// Add token in response
		response.addHeader("Authorization", "Bearer " + token);
	}
}
