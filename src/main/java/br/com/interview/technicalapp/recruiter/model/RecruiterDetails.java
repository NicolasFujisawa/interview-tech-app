package br.com.interview.technicalapp.recruiter.model;

import java.util.Collection;
import java.util.Collections;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class RecruiterDetails implements UserDetails {

    private final transient Recruiter recruiter;

    public RecruiterDetails(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("Recruiter"));
    }

    @Override
    public String getPassword() {
        return recruiter.getPassword();
    }

    @Override
    public String getUsername() {
        return recruiter.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return recruiter.isEnabled();
    }
}
