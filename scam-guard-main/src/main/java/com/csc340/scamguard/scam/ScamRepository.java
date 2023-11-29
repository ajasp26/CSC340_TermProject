package com.csc340.scamguard.scam;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Kenny Banks
 */
public interface ScamRepository extends JpaRepository<Scam, Long> {

    //get all scams that have associated business by title
    @Query("SELECT s FROM Scam s WHERE s.associated_business = ?1")
    List<Scam> getAllScamsByBusinessTitle(String title);
}
