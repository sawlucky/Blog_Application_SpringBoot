package com.blogapplication.blogapplicationapi.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.blogapplication.blogapplicationapi.Security.CustomerDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	 @Autowired
	    private CustomerDetailService customerDetailService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
				.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated() // Require authentication for
																							// all endpoints
				).httpBasic(httpBasic -> {
				}); // Enable HTTP Basic Authentication

		return http.build();
	}
//	  @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	       
//	        http.csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity (not recommended in production)
//	            .authorizeHttpRequests((auth) -> auth
//	                .requestMatchers("/api/**").permitAll()  // Allow access to /api/17 without authentication
//	                .anyRequest().authenticated()  // All other endpoints require authentication
//	            )
//	            .httpBasic(httpBasic -> {
//					}); // Enable Basic Auth
//
//	        return http.build();
//	    }
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		// Create an in-memory user with username "lucky" and password "lucky"
////		@SuppressWarnings("deprecation")
////		UserDetails user = User.withDefaultPasswordEncoder() // Use default password encoder (plain text)
////				.username("lucky").password("lucky").roles("USER", "ADMIN") // Add a role (optional but recommended)
////				.build();
//
//		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("password")).roles("USER").build();
//		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}

	@SuppressWarnings("removal")
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(customerDetailService)
            .passwordEncoder(new BCryptPasswordEncoder())
            .and()
            .build();
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
