package com.hobbylobby.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hobbylobby.domain.Authority;
import com.hobbylobby.domain.Hobby;
import com.hobbylobby.domain.User;
import com.hobbylobby.repository.UserRepository;

import org.springframework.security.acls.model.*;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
	private PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(Long id) {

        return userRepository.findById(id);
    }

    public List<User> findUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public List<User> sortUsersByName(List<User> users) {

        Collections.sort(users, new Comparator<User>(){
            @Override
            public int compare(User user1, User user2) {
                return user1.getName().compareTo(user2.getName());
            }
        });

        return users;
    }

    public List<String> sortUsersByName2(List<String> users) {

        Collections.sort(users, new Comparator<String>(){
            @Override
            public int compare(String user1, String user2) {
                return user1.compareTo(user2);
            }
        });

        return users;
    }

    public User registerUser(User user) throws AlreadyExistsException {

		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		user.getAuthorities().add(authority);

		List<User> users = userRepository.findByUsername(user.getUsername());

        if(users.size()==0) {

		    return userRepository.save(user);
        } else {

            throw new AlreadyExistsException("Username already exists!");
        }
	}

    public void addHobby(User user, Optional<Hobby> hobbyOpt) {

        if(hobbyOpt.isPresent()) {

            Hobby hobby=hobbyOpt.get();

            user.getMyHobbies().add(hobby);
            hobby.getUsers().add(user);

            userRepository.save(user);
            hobbyService.save(hobby);
        }

        return;
    }

    public void removeHobby(User user, Hobby hobby) {

        user.getMyHobbies().remove(hobby);
        hobby.getUsers().remove(user);

        userRepository.save(user);
        hobbyService.save(hobby);

        return;
    }

    public void addConnection(User user1, User user2) {

        user1.getConnections().add(user2);
        userRepository.save(user1);
        
        user1.setUser(user1);

        return;
    }

    public void removeConnection(User user1, User user2) {

        user1.getConnections().remove(user2);
        userRepository.save(user1);

        return;
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public void deleteUser(User user) {

        userRepository.deleteById(user.getId());
        return;
    }
	
}
