package com.avijit.assessment.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৮/১১/২১
 */
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
