package com.example.demo.service.user.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import com.example.demo.service.user.SecurityService;



/**
 * SecurityServiceImpl class
 *
 * @author Faruk Hossain
 *
 */

@Service
public class SecurityServiceImpl implements SecurityService {
//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	public static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		logger.info("SecurityServiceImpl: findLoggedInUsername start");
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		logger.info("SecurityServiceImpl: findLoggedInUsername end");
		if (userDetails instanceof UserDetails && !(StringUtils.isEmpty(userDetails))) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	@Override
	public boolean autologin(String username, String password) {
		logger.info("SecurityServiceImpl: autologin start");
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());

		try {
//			facing some problem with authentication manager. That's why comment
//			Field authenticationManager in com.example.demo.service.user.impl.SecurityServiceImpl 
//			required a bean of type 'org.springframework.security.authentication.AuthenticationManager' 
//			that could not be found.
//			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (AuthenticationException e) {
			return false;
		}

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
			return true;
		}
		logger.info("SecurityServiceImpl: autologin end");
		return false;
	}
}
