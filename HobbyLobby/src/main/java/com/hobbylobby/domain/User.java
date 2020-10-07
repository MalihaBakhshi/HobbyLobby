package com.hobbylobby.domain;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	private Long id;
	private String name;
	private String username;
	private String password; 
	private Set<String> connections=new HashSet<>();
	private Set<Long> myHobbies=new HashSet<>();
	private Set<Authority> authorities = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getConnections() {
		return connections;
	}
	public void setConnections(Set<String> connections) {
		this.connections = connections;
	}
	public Set<Long> getMyHobbies() {
		return myHobbies;
	}
	public void setMyHobbies(Set<Long> myHobbies) {
		this.myHobbies = myHobbies;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
}
