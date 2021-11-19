package com.avijit.assessment.security;

import com.avijit.assessment.reposiroty.UserRepository;
import com.avijit.assessment.schema.User;
import com.avijit.assessment.util.BaseService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */

@Service
public class UserDetailsServiceImpl extends BaseService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(getMessage("user.not.found")));
		return user;
	}
}