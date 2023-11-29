package com.csc340.scamguard.scam;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kenny Banks
 */
@Service
public class ScamService {

    @Autowired
    private ScamRepository repo;

    /**
     * Get all scams.
     *
     * @return the list of scams.
     */
    public List<Scam> getAllScams() {
        return repo.findAll();
    }

    /**
     * Get a single scam by ID
     *
     * @param scamId
     * @return the scam
     */
    public Scam getScam(long scamId) {
        return repo.getReferenceById(scamId);
    }

    /**
     * Delete a single scam by ID
     *
     * @param scamId
     */
    public void deleteScam(long scamId) {
        repo.deleteById(scamId);
    }

    /**
     * Save a scam entry.
     *
     * @param scam
     */
    void saveScam(Scam scam) {
        repo.save(scam);
    }

    public List<Scam> getAllScamsByBusinessTitle(String title) {
        return repo.getAllScamsByBusinessTitle(title);
    }

}
