package com.avijit.assessment.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৬/১১/২১
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //@Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_at")
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private OffsetDateTime created;

    @PrePersist
    public void setCreated() {
        this.created = OffsetDateTime.now();
    }
}
