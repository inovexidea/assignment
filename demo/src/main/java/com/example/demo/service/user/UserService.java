package com.example.demo.service.user;

import java.util.List;

import com.example.demo.entity.user.User;


/**
 * UserService interface
 *
 * @author Faruk Hossain
 *
 */
public interface UserService {
	User save(User user) throws Exception;

	User findByUsername(String username) throws Exception;

	List<User> getAllUser() throws Exception;

	User searchUser(String userName) throws Exception;
	int searchUserByNameAndPassword(String searchName,String password) throws Exception;

}
