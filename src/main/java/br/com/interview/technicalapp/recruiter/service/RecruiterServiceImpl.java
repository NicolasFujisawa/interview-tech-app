package br.com.interview.technicalapp.recruiter.service;

import br.com.interview.technicalapp.recruiter.model.Recruiter;
import br.com.interview.technicalapp.recruiter.repository.RecruiterRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Override
    public List<Recruiter> findAll() {
        return this.recruiterRepository.findAll();
    }

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
