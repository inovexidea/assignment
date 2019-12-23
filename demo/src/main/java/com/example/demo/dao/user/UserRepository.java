package com.example.demo.dao.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.User;


/**
 * UserRepository interface
 *
 * @author Faruk Hossain
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	@Query(value = "SELECT * FROM user INNER JOIN user_role ON user_role.user_id=user.id INNER JOIN role ON role.id=user_role.role_id WHERE not role.name='ROLE_ADMIN' ORDER BY user.active", nativeQuery = true)
	List<User> findAllUsersByUserRole();

	@Query(value = "SELECT * FROM user WHERE username LIKE CONCAT('%',LOWER(?1),'%') ORDER BY id ASC", nativeQuery = true)
	User searchUserByName(String searchName);
	
	@Query(value = "SELECT count(*) FROM user WHERE username = ?1 AND password = ?2 ", nativeQuery = true)
	int searchUserByNameAndPassword(String searchName,String password);

}
