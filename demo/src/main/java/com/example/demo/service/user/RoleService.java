package com.example.demo.service.user;

import java.util.List;

import com.example.demo.entity.user.Role;


/**
 * RoleService interface
 *
 * @author Faruk Hossain
 *
 */
public interface RoleService {
	List<Role> getAllRole() throws Exception;
	String findRoleById(String username);
	Role findByRoleId(long id);
}
