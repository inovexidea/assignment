package com.example.demo.dao.user;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.Role;



/**
 * RoleRepository interface
 *
 * @author Faruk Hossain
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Set<Role> findRoleByName(String name);
	
	@Query(value = "select r.name from user u inner join user_role ur on(u.id=ur.user_id) inner join role r on(ur.role_id=r.id) where u.username=?1 ", nativeQuery = true)
	String findRoleById(String username);

}
