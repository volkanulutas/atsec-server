package com.vem.atsecserver.repository.user;

import com.vem.atsecserver.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author volkanulutas
 * @since 12.12.2020
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Boolean existsByUsernameIgnoreCase(String username);
}
