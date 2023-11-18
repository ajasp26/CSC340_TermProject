package com.csc340.scamguard.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kenny Banks
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
