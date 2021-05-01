package com.hms.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hms.gateway.security.AuthEntryPointJwt;
import com.hms.gateway.security.AuthTokenFilter;
import com.hms.gateway.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	     authProvider.setUserDetailsService(userDetailsService());
	     authProvider.setPasswordEncoder(passwordEncoder());
	         
	     return authProvider;
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
        	.authorizeRequests()
        			.antMatchers("/receptionist/").hasAnyAuthority("ADMIN","MANAGER","RECEPTIONIST")
        			.antMatchers("/manager/").hasAnyAuthority("ADMIN","MANAGER")
            		.antMatchers("/admin/").hasAuthority("ADMIN")
            		.antMatchers("/api/auth/**").permitAll()
            		.anyRequest().authenticated()
            		.and().sessionManagement()
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            		.and()
            		.formLogin().permitAll()
            		.and()
            		.logout().permitAll()
            		.and()
            		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
            
	
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	

}
