package br.com.interview.technicalapp.user.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.interview.technicalapp.candidate.model.Candidate;
import br.com.interview.technicalapp.recruiter.model.Recruiter;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserDetailsImpl<T extends User> implements UserDetails {

    private final transient T user;

    public UserDetailsImpl(T user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof Recruiter) {
            authorities.add(new SimpleGrantedAuthority("Recruiter"));
        }
        if (user instanceof Candidate) {
            authorities.add(new SimpleGrantedAuthority("Candidate"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
        return this.user.isEnabled();
    }
}
