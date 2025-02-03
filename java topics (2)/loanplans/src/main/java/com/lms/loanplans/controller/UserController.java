package com.lms.loanplans.controller;
 
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.loanplans.entities.MyUser;
import com.lms.loanplans.helper.AuthRequest;
import com.lms.loanplans.helper.MyToken;
import com.lms.loanplans.service.JwtService;
import com.lms.loanplans.service.MyUserDetailsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
 
@RestController

public class UserController {
	@Autowired
	private MyUserDetailsService us;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
 
	@GetMapping("/v1/home")
	public String home() {
		return "Everybody is welcome";
	}
 
	@GetMapping("/v2/customer/home")
	@PreAuthorize("hasAuthority('CUSTOMER')")
	public String userHome(Authentication authentication) {
		String username=authentication.getName();
		System.out.println(username);
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		for(GrantedAuthority role:roles)
		{
			System.out.println(role.getAuthority());
		}
		return "Customer home page";
	}
 
	@GetMapping("/v2/bank manager/home")
	@PreAuthorize("hasAuthority('BANK MANAGER')")
	@SecurityRequirement(name = "Bearer Authentication")
	public String adminHome(Authentication authentication) {
		String username=authentication.getName();
		System.out.println(username);
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		for(GrantedAuthority role:roles)
		{
			System.out.println(role.getAuthority());
		}
		return "Hi Bank Manager, welcome";
	}
 
	@PostMapping("/v1/signup")
	public MyUser signup(@RequestBody MyUser myUser) {
 
		return us.addNewUser(myUser);
	}
 
	@PostMapping("/v1/login")
	public MyToken login(@RequestBody AuthRequest authRequest)
	{
		MyToken token=new MyToken();
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if(auth.isAuthenticated())
		{
			String jwt=jwtService.generateToken(authRequest.getUsername());
			token.setUsername(authRequest.getUsername());
			token.setToken(jwt);
			token.setAuthorities(auth.getAuthorities());
		}else
		{
			throw new UsernameNotFoundException("Login failed");
		}
		return token;
	}
}
 
 
