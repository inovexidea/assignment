package com.example.demo.service.user.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.user.RoleRepository;
import com.example.demo.dao.user.UserRepository;
import com.example.demo.entity.user.User;
import com.example.demo.service.user.UserService;



/**
 * UserServiceImpl class
 *
 * @author Faruk Hossain
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User save(User user) throws Exception {
		// lombok design pattern is used
		// for eclipse.. 
		/**
		 * Downloaded jar from https://projectlombok.org/download or use the jar which is downloaded from your maven build.
		 * Execute command in terminal: java -jar lombok.jar
         * This command will open window as show in the picture below, install and quit the installer and restart eclipse.
		 * https://www.journaldev.com/18124/java-project-lombok
		 */
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findRoleByName("ROLE_USER")));
		user.setActive(true);
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getAllUser() throws Exception {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User searchUser(String userName) {
		System.out.println(userName);
		return userRepository.searchUserByName(userName);
	}

	@Override
	public int searchUserByNameAndPassword(String searchName, String password) throws Exception {
		
		return userRepository.searchUserByNameAndPassword(searchName, password);
	}

}
