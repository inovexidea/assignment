package com.example.demo.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.user.RoleRepository;
import com.example.demo.entity.user.Role;
import com.example.demo.service.user.RoleService;



/**
 * RoleServiceImpl class
 *
 * @author Faruk Hossain
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAllRole() throws Exception {
		return roleRepository.findAll();
	}

	@Override
	public Role findByRoleId(long id) {
		return roleRepository.getOne(id);
	}

	@Override
	public String findRoleById(String username) {
		// TODO Auto-generated method stub
		return roleRepository.findRoleById(username);
	}
}
