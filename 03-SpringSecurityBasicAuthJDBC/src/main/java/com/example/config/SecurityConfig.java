package com.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

		// Inject data source autoconfigured by spring boot
		@Bean
		public UserDetailsManager userDetailsManager(DataSource dataSource) {
			// Tell to spring security to use JDBC authentication with our data source
			// No hard coding users.
			return new JdbcUserDetailsManager(dataSource);
		}
		
		// Restricting Access to Roles 
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(configurer -> 
				configurer
					.requestMatchers(HttpMethod.DELETE, "/employee/delete/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET, "employee/findById/**").hasRole("EMPLOYEE")
					.requestMatchers(HttpMethod.GET, "/employee/all").hasRole("EMPLOYEE")
					.requestMatchers(HttpMethod.GET, "/home").permitAll()
					.requestMatchers(HttpMethod.POST, "/employee").hasRole("MANAGER")
					.requestMatchers(HttpMethod.PUT, "/employee").hasRole("MANAGER")
					.requestMatchers("/h2-console/**").permitAll());

			// Use Http Basic Authentication
			http.httpBasic(Customizer.withDefaults());
			
			// disable csrf - not required in REST API's
			http.csrf(csrf->csrf.disable());
			
			return http.build();

		}
}
