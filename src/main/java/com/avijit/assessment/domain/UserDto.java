package com.avijit.assessment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserDto {

    private Long id;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    private String phone;

    private String password;

    @JsonProperty("maximum_book_access")
    private int maximumBookAccess;

    Set<Long> roles;
}
