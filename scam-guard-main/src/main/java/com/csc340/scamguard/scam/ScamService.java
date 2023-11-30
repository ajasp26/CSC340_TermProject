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
     * Get all scams that match the keyword.
     *
     * @param keyword the search term.
     * @return the list of scams.
     */
    public List<Scam> getAllScams(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }


    /**
     * Get all scams from user.
     *
     * @return the list of scams from a specific user.
     */
    public List<Scam> getScamsFrom(String username) {
        return repo.findByPostedBy(username);
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

}
