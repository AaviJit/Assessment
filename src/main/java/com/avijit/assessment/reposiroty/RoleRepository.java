package com.avijit.assessment.reposiroty;

import com.avijit.assessment.schema.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
