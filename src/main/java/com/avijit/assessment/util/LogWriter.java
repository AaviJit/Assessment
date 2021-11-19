package com.avijit.assessment.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৮/১১/২১
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogWriter extends OncePerRequestFilter {

    private static final String REQUEST_ID = "request_id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String value = request.getHeader(REQUEST_ID);
        MDC.put(REQUEST_ID, value);
        filterChain.doFilter(request, response);
    }

    public static void printRequestHeader(String layerName) {
        log.error(MDC.get(REQUEST_ID) + " in " + layerName);
    }
}
