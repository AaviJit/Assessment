package com.avijit.assessment.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Getter
@Setter
@Accessors(chain = true)
public class IssueBookRequest {

    Long userId;

    Long bookId;
}
