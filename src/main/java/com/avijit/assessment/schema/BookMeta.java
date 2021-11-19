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
public class BookMeta extends BaseEntity {

    private Long bookId;

    private String authorName;

    private String publisherName;

    private String isbnNumber;
}
