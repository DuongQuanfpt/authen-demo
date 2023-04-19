package com.example.internservice.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetail implements UserDetails {

    private Intern intern;

    public MyUserDetail() {
    }

    public MyUserDetail(Intern intern) {
        this.intern = intern;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(intern.getRoles().name()));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return intern.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return intern.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
//        return intern.isActive();
        return intern.isActive();
    }

}
