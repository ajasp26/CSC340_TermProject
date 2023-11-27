package com.csc340.scamguard.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {

    @Autowired
    AdminRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Admin> getAllAdmins() {return repo.findAll();}

    public Admin getAdminByName(String name) {
        return repo.findByName(name).orElseThrow(()
                -> new UsernameNotFoundException(name + "not found"));
    }

    public Admin getAdmin(Long id) {
        return repo.getReferenceById(id);
    }

    public void deleteAdmin(long id) {repo.deleteById(id);}

    public void saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        repo.save(admin);
    }

    public void updateAdmin(Admin admin) {
        Admin existing = repo.getReferenceById(admin.getId());
        if (admin.getName() != null) {
            existing.setName(admin.getName());
        }
        if (admin.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        if (admin.getEmail() != null) {
            existing.setEmail(admin.getEmail());
        }
        repo.save(existing);
    }

}
