package com.csc340.scamguard.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Kenny Banks
 */
@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;



    /**
     * Get all users
     *
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    /**
     * Find one user by ID.
     *
     * @param id
     * @return the user
     */
    public User getUser(long id) {
        return repo.getReferenceById(id);
    }

    /**
     * Delete user by ID.
     *
     * @param id The ID of the user to delete
     * @return true if the user was deleted, false if the user did not exist
     */
    public boolean deleteUser(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Save user entry.
     *
     * @param user
     */
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }

    /**
     * Update existing user.
     *
     * @param user
     */
    public void updateUser(User user) {
        User existing = repo.getReferenceById(user.getId());
        if (user.getUserName() != null) {
            existing.setUserName(user.getUserName());
        }
        if (user.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        repo.save(existing);
    }

    public User getUserByUserName(String userName) {
        return repo.findByUserName(userName).orElseThrow(()
                -> new UsernameNotFoundException(userName + "not found"));
    }


}
