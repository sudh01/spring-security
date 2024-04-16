package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Customized security configuration
/*
 * In spring security 
 */
@Configuration
public class SecurityConfig {
	
	// Swagger Config
	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/v3/api-docs",  
            "/swagger-resources/**", 
            "/swagger-ui/**",
             };

	// Create user accounts in in-memory
	/*
	 * InMemoryUserDetailsManager: - Stores credentials in memory - Implements
	 * UserDetailsService(I) to provide support for username/password based
	 * authentication that is stored in memory. - It is mainly intended for testing
	 * and demonstration purposes.
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails ram = User.builder()
				.username("ram")
				.password("{noop}abc.123")
				.roles("EMPLOYEE").build();

		// {noop} - passwords stored in-memory in plain text format
		UserDetails sam = User.builder()
				.username("sam")
				.password("{noop}abc.123")
				.roles("EMPLOYEE", "ADMIN")
				.build();

		UserDetails raj = User.builder()
				.username("raj")
				.password("{noop}abc.123")
				.roles("EMPLOYEE", "MANAGER")
				.build();

		return new InMemoryUserDetailsManager(ram, sam, raj);

	}

	// Restricting Access to Roles 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> 
			configurer
				
				.requestMatchers(HttpMethod.POST, "/employee").hasRole("MANAGER")
				.requestMatchers(HttpMethod.PUT, "/employee").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/employee/delete/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "employee/findById/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/employee/all").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/home").permitAll()
				
				);

		// Use Http Basic Authentication
		http.httpBasic(Customizer.withDefaults());
		http.formLogin(Customizer.withDefaults());
		// disable csrf - not required in REST API's
		//http.csrf(csrf->csrf.disable());
		
		return http.build();

	}
}