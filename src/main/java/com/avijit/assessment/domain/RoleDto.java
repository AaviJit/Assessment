package com.avijit.assessment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৮/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
public class RoleDto {
    private Long id;
    private String name;
}
