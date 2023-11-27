package com.csc340.scamguard.security;

import java.util.ArrayList;
import java.util.Optional;

import com.csc340.scamguard.admin.Admin;
import com.csc340.scamguard.admin.AdminRepository;
import com.csc340.scamguard.business.Business;
import com.csc340.scamguard.business.BusinessRepository;
import com.csc340.scamguard.user.User;
import com.csc340.scamguard.user.UserRepository;
import com.csc340.scamguard.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

/**
 * @author Kenny Banks, Derek Fox
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
        Optional<Client> optionalClient = tryGetClient(username);

        if (optionalClient.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        Client client = optionalClient.get();
        String name;
        String password;
        ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();

        if (client instanceof User) {
            authList.add(new SimpleGrantedAuthority("USER"));
            name = ((User) client).getUserName();
            password = ((User) client).getPassword();
        } else if (client instanceof Business) {
            authList.add(new SimpleGrantedAuthority("BUSINESS"));
            name = ((Business) client).getTitle();
            password = ((Business) client).getPassword();
        } else if (client instanceof Admin) {
            authList.add(new SimpleGrantedAuthority("ADMIN"));
            name = ((Admin) client).getName();
            password = ((Admin) client).getPassword();
        } else {
            throw new IllegalStateException("Unknown client type");
        }

        return new org.springframework.security.core.userdetails.User(
                name, password, authList);
    }

    private Optional<Client> tryGetClient(String username) {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (optionalUser.isPresent()) {
            return Optional.of(optionalUser.get());
        }

        Optional<Business> optionalBusiness = businessRepository.findByTitle(username);
        if (optionalBusiness.isPresent()) {
            return Optional.of(optionalBusiness.get());
        }

        Optional<Admin> optionalAdmin = adminRepository.findByName(username);
        if (optionalAdmin.isPresent()) {
            return Optional.of(optionalAdmin.get());
        }

        return Optional.empty();
    }
}
