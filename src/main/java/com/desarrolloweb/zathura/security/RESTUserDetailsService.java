package com.desarrolloweb.zathura.security;

import java.util.HashMap;
import java.util.Map;

import com.desarrolloweb.zathura.models.POJOs.User;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RESTUserDetailsService implements UserDetailsService {
	Map<String, User> users = new HashMap<>();

	public RESTUserDetailsService() {
		super();
		users.put("user", new User("user", "contra", "ROLE_USER"));
		users.put("admin", new User("admin", "contra", "ROLE_ADMIN"));
		users.put("mod", new User("mod", "contra", "ROLE_MOD"));
	}
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO En este método debería recuperarlse la info del usuario desde la base de datos
		
		System.out.println("*** Retrieving user");
		return users.get(username);
	}	

}
