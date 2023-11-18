package com.csc340.scamguard.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author Derek Fox
 */
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByTitle(String title);
}
