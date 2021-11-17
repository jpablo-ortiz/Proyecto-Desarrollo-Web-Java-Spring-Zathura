package com.desarrolloweb.zathura.security;

import com.desarrolloweb.zathura.models.Tripulante;
import com.desarrolloweb.zathura.models.POJOs.User;
import com.desarrolloweb.zathura.service.TripulanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RESTUserDetailsService implements UserDetailsService {
	//Map<String, User> users = new HashMap<>();

	@Autowired
	private TripulanteService tripulanteService;

	public RESTUserDetailsService() {
		super();
		//users.put("user", new User("user", "contra", "ROLE_USER"));
		//users.put("admin", new User("admin", "contra", "ROLE_ADMIN"));
		//users.put("mod", new User("mod", "contra", "ROLE_MOD"));
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO En este método debería recuperarlse la info del usuario desde la base de datos
		System.out.println("*** Retrieving user");
		//return users.get(username);

		Tripulante tripulante = tripulanteService.obtenerTripulanteByUserName(username);
		return User.tripulanteAUser(tripulante);
	}	

}
