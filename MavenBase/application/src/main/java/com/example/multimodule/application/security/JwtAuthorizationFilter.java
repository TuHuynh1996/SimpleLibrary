package com.example.multimodule.application.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.library.entity.Users;
import com.example.library.repository.UserRepository;

/**
 * The Class JwtAuthorizationFilter.
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	/** The user repository. */
	private UserRepository userRepository;
	
	/** The secret key. */
	@Value("${web.module.jwtproperties.secret}")
	private String secretKey;

	/**
	 * Instantiates a new jwt authorization filter.
	 *
	 * @param authenticationManager the authentication manager
	 * @param userRepository the user repository
	 */
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.www.BasicAuthenticationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Read the Authorization header, where the JWT token should be
		String header = request.getHeader("Authorization");

		// If header does not contain BEARER or is null delegate to Spring impl and exit
		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		// If header is present, try grab user principal from database and perform
		// authorization
		Authentication authentication = getUsernamePasswordAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Continue filter execution
		chain.doFilter(request, response);
	}

	/**
	 * Gets the username password authentication.
	 *
	 * @param request the request
	 * @return the username password authentication
	 */
	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization").replace("Bearer ", "");

		if (token != null) {
			// parse the token and validate it
			String userName = JWT.require(Algorithm.HMAC512(secretKey.getBytes())).build().verify(token).getSubject();

			// Search in the DB if we find the user by token subject (username)
			// If so, then grab user details and create spring auth token using username,
			// pass, authorities/roles
			if (userName != null) {
				Users user = userRepository.findByName(userName);
				UserPrincipal principal = new UserPrincipal(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
						principal.getAuthorities());
				return auth;
			}
			return null;
		}
		return null;
	}
}
