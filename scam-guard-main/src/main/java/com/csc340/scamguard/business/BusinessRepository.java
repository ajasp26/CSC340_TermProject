package com.csc340.scamguard.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author csc340-f23
 */
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByTitle(String Title);
}
