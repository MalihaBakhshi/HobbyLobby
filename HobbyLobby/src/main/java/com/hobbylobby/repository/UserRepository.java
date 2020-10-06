package com.hobbylobby.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hobbylobby.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findByUsername(String username);
    public List<User> findByName(String name);
	
}
