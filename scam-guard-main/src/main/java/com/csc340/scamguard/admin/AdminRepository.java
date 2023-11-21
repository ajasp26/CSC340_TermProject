package com.csc340.scamguard.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
//    Optional<Admin> findByTitle(String title);
}