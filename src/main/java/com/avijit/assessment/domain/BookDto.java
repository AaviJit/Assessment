package com.avijit.assessment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BookDto {

    private Long id;

    private String name;

    @JsonProperty("total_stock")
    private int totalStock;
}
