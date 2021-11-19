package com.avijit.assessment.reposiroty;

import com.avijit.assessment.schema.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
public interface BookRepository extends JpaRepository<Book,Long> {
}
