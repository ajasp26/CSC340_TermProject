package com.csc340.scamguard.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Derek Fox
 */
@Service
public class BusinessService {

    @Autowired
    BusinessRepository repo;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Get all users
     *
     * @return the list of users.
     */
    public List<Business> getAllBusinesses() {
        return repo.findAll();
    }

    /**
     * Find one user by ID.
     *
     * @param id
     * @return the user
     */
    public Business getBusiness(long id) {
        return repo.getReferenceById(id);
    }

    /**
     * Delete user by ID.
     *
     * @param id
     */
    public void deleteBusiness(long id) {
        repo.deleteById(id);
    }

    /**
     * Save user entry.
     *
     * @param business
     */
    public void saveBusiness(Business business) {
        business.setPassword(passwordEncoder.encode(business.getPassword()));
        business.setPending(true);
        repo.save(business);
    }

    /**
     * Update existing user.
     *
     * @param business
     */
    public void updateBusiness(Business business) {
        Business existing = repo.getReferenceById(business.getId());
        if (business.getTitle() != null) {
            existing.setTitle(business.getTitle());
        }
        if (business.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(business.getPassword()));
        }
        if (business.getEmail() != null) {
            existing.setEmail(business.getEmail());
        }
//        if (business.getRole() != null) {
//            existing.setRole(business.getRole());
//        }
        repo.save(existing);
    }

    public Business getBusinessByTitle(String title) {
        return repo.findByTitle(title).orElseThrow(()
                -> new UsernameNotFoundException(title + "not found"));
    }
}
