package com.avijit.assessment.schema;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৬/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Role extends BaseEntity{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
}
