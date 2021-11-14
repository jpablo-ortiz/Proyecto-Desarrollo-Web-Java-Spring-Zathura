package com.desarrolloweb.zathura.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// Ver http://www.baeldung.com/securing-a-restful-web-service-with-spring-security
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile(value = { "default", "unit-test" })
public class SecConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RESTAuthenticationProvider authenticationProvider;
	
	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private RESTLogoutSuccessHandler logoutSuccessHandler;

	@Autowired
	private RESTUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors() //.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
			.and()
			.csrf().disable()  // Para mejorar seguridad, es recomendable activar CSRF, aunque eso requiere de varios cambios a la configuración actual
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
			.and()
				.authorizeRequests()
					.antMatchers("/h2/**", "/console/**", "/public/**").permitAll()
					.antMatchers("/login/**").permitAll()
					.antMatchers(HttpMethod.POST, "/tripulante").permitAll()
					.antMatchers(HttpMethod.GET,  "/tripulante/{\\d+}").permitAll()
					.antMatchers(HttpMethod.POST, "/tripulante/{\\d+}").permitAll()
					.antMatchers(HttpMethod.GET,  "/modelo-nave").permitAll()
					.antMatchers(HttpMethod.POST, "/modelo-nave").permitAll()
					.antMatchers(HttpMethod.GET,  "/nave").permitAll()
					.antMatchers(HttpMethod.POST, "/nave").permitAll()
					.antMatchers(HttpMethod.GET,  "/nave/tripulante/{\\d+}/nave/{\\d+}").permitAll()
					.anyRequest().authenticated()
			.and()
				.formLogin()
					.successHandler(authenticationSuccessHandler)
					.failureHandler(new SimpleUrlAuthenticationFailureHandler())
			.and()
				.logout()
					.logoutSuccessHandler(logoutSuccessHandler);
				
		
		http.addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable();
        http.headers().frameOptions().disable();
	}

	@Bean
	public CorsFilter corsFilter() {
		// ver https://stackoverflow.com/a/42053745
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	//@Resource
	//public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.inMemoryAuthentication().withUser("usuario1").password("1234").roles("ROL_CAPITAN");
	//}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return userDetailsService;
	}

}
