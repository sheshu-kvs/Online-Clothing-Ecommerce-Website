package com.sheshu.service;

import com.sheshu.model.User;
import java.util.List;
import java.util.Optional;

	public interface UserService1 {
		User createUser(User user);
	    User getUserById(Long id);
	    List<User> getAllUsers();
	    void deleteUser(Long id);
	    Optional<User> findById(Long id);
	    User getCurrentUser();
	    User authenticate(String username, String password);
	    void saveUser(User user);
		User findByUsername(String username);
		User registerUser(User user, boolean isAdmin);
		
		 long countAllUsers();
		void updateUserRole(Long id, String role);
	}
