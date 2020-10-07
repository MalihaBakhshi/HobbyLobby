package com.hobbylobby.domain;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hobbies")
public class Hobby {

	private Long id;
	private String name;
    private String about;
	private Set<Long> users=new HashSet<>();
	private Set<Long> posts=new HashSet<>();

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

    public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<Long> getUsers() {
		return users;
	}

	public void setUsers(Set<Long> users) {
		this.users = users;
	}

	public Set<Long> getPosts() {
		return posts;
	}

	public void setPosts(Set<Long> posts) {
		this.posts = posts;
	}

}
