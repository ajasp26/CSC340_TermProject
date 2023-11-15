package com.csc340.scamguard.alert;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Derek Fox
 */
@Service
public class AlertService {

    @Autowired
    private AlertRepository repo;

    /**
     * Get all alerts.
     *
     * @return the list of alerts.
     */
    public List<Alert> getAllAlerts() {
        return repo.findAll();
    }

    /**
     * Get all alerts that match the keyword.
     *
     * @param keyword the search term.
     * @return the list of alerts.
     */
    public List<Alert> getAllAlerts(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    /**
     * Get a single alert by ID
     *
     * @param alertId
     * @return the alert
     */
    public Alert getAlert(long alertId) {
        return repo.getReferenceById(alertId);
    }

    /**
     * Delete a single alert by ID
     *
     * @param alertId
     */
    public void deleteAlert(long alertId) {
        repo.deleteById(alertId);
    }

    /**
     * Save an alert entry.
     *
     * @param alert
     */
    void saveAlert(Alert alert) {
        repo.save(alert);
    }

}
