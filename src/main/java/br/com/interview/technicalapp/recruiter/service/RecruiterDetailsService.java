package br.com.interview.technicalapp.recruiter.service;

import br.com.interview.technicalapp.recruiter.model.Recruiter;
import br.com.interview.technicalapp.recruiter.model.RecruiterDetails;
import br.com.interview.technicalapp.recruiter.repository.RecruiterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RecruiterDetailsService implements UserDetailsService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    public RecruiterDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        Recruiter recruiter = this.recruiterRepository.findByUsername(username);
        if (recruiter == null) {
            throw new UsernameNotFoundException(username);
        }
        return new RecruiterDetails(recruiter);
    }
}
