package com.example.demo.entity.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Role entity class
 *
 * @author Faruk Hossain
 *
 */
@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
@AllArgsConstructor
public class Role extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
}
