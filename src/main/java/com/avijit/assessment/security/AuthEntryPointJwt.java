package com.avijit.assessment.security;

import com.avijit.assessment.util.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */

@Component
@Slf4j
public class AuthEntryPointJwt extends BaseService implements AuthenticationEntryPoint {


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, getMessage("authentication.failed"));
	}
}