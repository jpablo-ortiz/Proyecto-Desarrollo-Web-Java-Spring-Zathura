package com.desarrolloweb.zathura.models.POJOs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.desarrolloweb.zathura.models.Tripulante;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// Ver http://www.baeldung.com/role-and-privilege-for-spring-security-registration
public class User implements UserDetails {
	private static final long serialVersionUID = 1987040876334251017L;

	private String username;
	private String password;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

	private List<GrantedAuthority> authorities = new ArrayList<>();

	public User(String username, String password, String... authorities) {
		super();
		this.username = username;
		this.password = password;
		for (String auth : authorities) {
			GrantedAuthority ga = new GrantedAuthority() {
				private static final long serialVersionUID = -3483137563784976405L;

				@Override
				public String getAuthority() {
					return auth;
				}
			};
			this.authorities.add(ga);
		}
	}
	
	static public User tripulanteAUser(Tripulante tripulante) {
		if (tripulante == null) {
			return null;
		}

		String rol = "";
		if (tripulante.getCapitan()) {
			rol = "ROLE_CAPITAN";
		}
		else if (tripulante.getNavegante()) {
			rol = "ROLE_NAVEGANTE";
		}
		else if (tripulante.getComerciante()){
			rol = "ROLE_COMERCIANTE";
		}

		return new User(
				tripulante.getUsername(),
				tripulante.getPassword(), 
				rol
			);
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

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(username + ": " + authorities);
		return authorities;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

}
