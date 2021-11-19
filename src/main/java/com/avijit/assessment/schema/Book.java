package com.avijit.assessment.schema;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

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
public class Book extends BaseEntity {

    private String name;

    private int totalStock;
}
