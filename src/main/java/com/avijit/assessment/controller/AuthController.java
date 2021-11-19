package com.avijit.assessment.controller;

import com.avijit.assessment.schema.User;
import com.avijit.assessment.security.JwtResponse;
import com.avijit.assessment.security.JwtUtils;
import com.avijit.assessment.security.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

	public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var jwtResponse = new JwtResponse()
                .setToken(jwt)
                .setId(userDetails.getId())
                .setUsername(userDetails.getUsername())
                .setEmail(userDetails.getEmail())
                .setRoles(roles)
                .setIssueTime(OffsetDateTime.now());
        return ResponseEntity.ok(jwtResponse);
    }

}