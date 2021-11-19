package com.avijit.assessment.schema;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.time.OffsetDateTime;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class UserBookHistory extends BaseEntity {

    private Long userId;

    private Long bookId;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private OffsetDateTime issueTime;

    private boolean hasReturned;
}
