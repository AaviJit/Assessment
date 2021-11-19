package com.avijit.assessment.reposiroty;

import com.avijit.assessment.schema.UserBookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.UUID;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
public interface UserBookHistoryRepository extends JpaRepository<UserBookHistory, Long> {

    List<UserBookHistory> findByUserIdAndHasReturned(Long userId, boolean hasReturned);

    List<UserBookHistory> findByBookIdAndHasReturned(Long bookId, boolean hasReturned);

}
