package com.example.demo.service.user;

/**
 * SecurityService interface
 *
 * @author Faruk Hossain
 *
 */
public interface SecurityService {
	String findLoggedInUsername();

	boolean autologin(String username, String password);
}
