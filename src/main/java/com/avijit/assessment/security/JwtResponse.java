package com.avijit.assessment.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৮/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
public class JwtResponse {
    private String token;
    private String type;
    private Long id;
    private String username;
    private String email;

    @JsonProperty("issue_time")
    @JsonFormat(pattern="dd-MM-yyyy")
    private OffsetDateTime issueTime;

    private List<String> roles;
}
