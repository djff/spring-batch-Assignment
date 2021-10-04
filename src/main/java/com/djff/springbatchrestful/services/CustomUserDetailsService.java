package com.djff.springbatchrestful.services;

import com.djff.springbatchrestful.models.CustomUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return CustomUser.getSampleUsers().stream().filter(u -> u.getUsername().equals(s)).findFirst().orElse(null);
    }
}
