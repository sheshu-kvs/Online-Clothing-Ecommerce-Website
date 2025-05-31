package com.sheshu.service.Impl;
import com.sheshu.model.User;
import java.util.Set;

import com.sheshu.repository.UserRepository;
import com.sheshu.service.UserService1;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService1 {

    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                      .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Wrap roleName inside a Set
        Set<String> roles = Collections.singleton(roleName);
        
        user.setRoles(roles);  // pass Set<String> instead of String
        
        userRepository.save(user);
    }

    
    
    @Override
    public void saveUser(User user) {
        // Encode password BEFORE saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("USER"));
        }
        
        userRepository.save(user);
    }
    @Override
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Debug output - add this temporarily
        System.out.println("=== AUTHENTICATION DEBUG ===");
        System.out.println("Input username: " + username);
        System.out.println("Stored username: " + user.getUsername());
        System.out.println("Input password: " + password);
        System.out.println("Stored password: " + user.getPassword());
        System.out.println("Password matches: " + passwordEncoder.matches(password, user.getPassword()));
        System.out.println("User enabled: " + user.isEnabled());
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        if (!user.isEnabled()) {
            throw new RuntimeException("Account disabled");
        }
        
        return user;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }




	@Override
	public User getCurrentUser() {
//		 TODO Auto-generated method stub
		return null;
	}


	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}


	  @Override
	    public long countAllUsers() {
	        return userRepository.count();
	    }

	  public User registerUser(User user, boolean isAdmin) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        
	        Set<String> roles = new HashSet<>();
	        roles.add("USER"); // Note: Without ROLE_ prefix (Spring will add it)
	        if (isAdmin) {
	            roles.add("ADMIN");
	        }
	        user.setRoles(roles);
	        
	        return userRepository.save(user);
	    }
}
