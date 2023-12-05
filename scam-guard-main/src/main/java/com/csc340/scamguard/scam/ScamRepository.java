package com.csc340.scamguard.scam;

import java.util.List;

import com.csc340.scamguard.alert.Alert;
import com.csc340.scamguard.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Kenny Banks
 */
public interface ScamRepository extends JpaRepository<Scam, Long> {
    @Query("SELECT s FROM Scam s WHERE s.posted_by = :userName")
    List<Scam> findByPostedBy(String userName);

    @Query("SELECT s FROM Scam s WHERE CONCAT(s.associated_business, s.description, s.location, s.posted_by, s.target, s.method, s.posted_on) LIKE %?1%")
    public List<Scam> search(String keyword);
  
    //get all scams that have associated business by title
    @Query("SELECT s FROM Scam s WHERE s.associated_business = ?1")
    List<Scam> getAllScamsByBusinessTitle(String title);
}
