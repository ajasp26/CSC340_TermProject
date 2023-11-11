package com.csc340.scamguard.security;

import java.util.ArrayList;


import com.csc340.scamguard.business.Business;
import com.csc340.scamguard.business.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author sentini
 */
@Service
public class BusinessDetailsService implements UserDetailsService {

    @Autowired
    private BusinessRepository repo;

    @Override
    public UserDetails loadUserByUsername(String title) throws UsernameNotFoundException {

        Business business = repo.findByTitle(title).orElseThrow(()
                -> new UsernameNotFoundException(title + "not found"));
        ArrayList<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("BUSINESS"));
        return new org.springframework.security.core.userdetails.User(
                business.getTitle(), business.getPassword(), authList);
    }

}
