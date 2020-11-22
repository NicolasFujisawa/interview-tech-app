package br.com.interview.technicalapp.recruiter.service;

import java.util.Optional;
import java.util.UUID;

import br.com.interview.technicalapp.recruiter.model.Recruiter;
import br.com.interview.technicalapp.recruiter.repository.RecruiterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    RecruiterRepository recruiterRepository;

    @Override
    public Recruiter save(Recruiter recruiter) {
        return this.recruiterRepository.save(recruiter);
    }

    @Override
    public void deleteById(UUID recruiterId) {
        this.recruiterRepository.deleteById(recruiterId);
    }

    @Override
    public Optional<Recruiter> findById(UUID recruiterId) {
        return this.recruiterRepository.findById(recruiterId);
    }
}
