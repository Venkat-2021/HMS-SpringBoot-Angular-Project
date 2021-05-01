package com.hms.gateway.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hms.gateway.model.EmployeeModel;

public class LoginUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public  LoginUserDetails(long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static LoginUserDetails build(EmployeeModel employee) {
		List<GrantedAuthority>  authorities = new ArrayList<GrantedAuthority>() ;
		authorities.add(new SimpleGrantedAuthority(employee.getRole()));

		System.out.println(authorities);
		return new  LoginUserDetails(
				employee.getId(), 
				employee.getEmployeeName(), 
				employee.getEmail(),
				employee.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(authorities);
		return authorities;
		
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LoginUserDetails logind = (LoginUserDetails) o;
		return Objects.equals(id, logind.id);
	}
}
