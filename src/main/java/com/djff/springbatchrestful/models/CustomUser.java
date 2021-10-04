package com.djff.springbatchrestful.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUser extends User {

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static List<CustomUser> getSampleUsers(){
        List<CustomUser> users = new ArrayList<>();
        SimpleGrantedAuthority adminRole = new SimpleGrantedAuthority("admin");
        users.add(new CustomUser("admin", "admin", new ArrayList<>(){{add(adminRole);}}));

        return users;
    }
}
