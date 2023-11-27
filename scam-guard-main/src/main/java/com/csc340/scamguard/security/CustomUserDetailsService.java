package com.csc340.scamguard.security;

import java.util.ArrayList;
import java.util.Optional;

import com.csc340.scamguard.admin.Admin;
import com.csc340.scamguard.admin.AdminRepository;
import com.csc340.scamguard.business.Business;
import com.csc340.scamguard.business.BusinessRepository;
import com.csc340.scamguard.user.User;
import com.csc340.scamguard.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

/**
 * @author Kenny Banks
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
            authList.add(new SimpleGrantedAuthority("USER"));
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(), user.getPassword(), authList);
        }

        Optional<Business> optionalBusiness = businessRepository.findByTitle(username);
        if (optionalBusiness.isPresent()) {
            Business business = optionalBusiness.get();
            ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
            authList.add(new SimpleGrantedAuthority("BUSINESS"));
            return new org.springframework.security.core.userdetails.User(
                    business.getTitle(), business.getPassword(), authList);
        }

        Optional<Admin> optionalAdmin = adminRepository.findByName(username);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
            authList.add(new SimpleGrantedAuthority("ADMIN"));
            return new org.springframework.security.core.userdetails.User(
                    admin.getName(), admin.getPassword(), authList);
        }

        throw new UsernameNotFoundException(username + " not found");
    }
}
