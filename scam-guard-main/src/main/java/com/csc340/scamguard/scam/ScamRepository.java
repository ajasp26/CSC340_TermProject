package com.csc340.scamguard.scam;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Kenny Banks
 */
public interface ScamRepository extends JpaRepository<Scam, Long> {

}
