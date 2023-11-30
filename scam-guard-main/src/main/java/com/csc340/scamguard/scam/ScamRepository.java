package com.csc340.scamguard.scam;

import java.util.List;

import com.csc340.scamguard.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Kenny Banks
 */
public interface ScamRepository extends JpaRepository<Scam, Long> {
    @Query("SELECT s FROM Scam s WHERE s.posted_by = :userName")
    List<Scam> findByPostedBy(String userName);
}
