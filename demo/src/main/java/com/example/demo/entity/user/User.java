package com.example.demo.entity.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity class
 *
 * @author Faruk Hossain
 *
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "age")
	private Integer age;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "password")
	private String password;
	@Transient
	private String passwordConfirm;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
}