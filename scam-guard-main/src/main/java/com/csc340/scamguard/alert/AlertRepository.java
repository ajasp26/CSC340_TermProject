package com.csc340.scamguard.alert;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Derek Fox
 */
public interface AlertRepository extends JpaRepository<Alert, Long> {

    public List<Alert> findByName(String name);

    @Query("SELECT p FROM Alert p WHERE CONCAT(p.name, p.method, p.location) LIKE %?1%")
    public List<Alert> search(String keyword);
}
