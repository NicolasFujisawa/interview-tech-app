package br.com.interview.technicalapp.candidate.controller.v1;

import br.com.interview.technicalapp.candidate.controller.v1.dto.CandidateRequest;
import br.com.interview.technicalapp.candidate.controller.v1.dto.CandidateResponse;
import br.com.interview.technicalapp.candidate.repository.exception.CandidateNotFoundException;
import br.com.interview.technicalapp.candidate.service.CandidateService;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> list() {
        var candidates = this.candidateService.findAll();

        return ResponseEntity.ok(candidates
                .stream()
                .map(CandidateResponse::parse)
                .collect(Collectors.toList()));
    }

    @PostMapping("/signup")
    public ResponseEntity<CandidateResponse> create(@Valid @RequestBody CandidateRequest candidateRequest) {
        candidateRequest.setPassword(passwordEncoder.encode(candidateRequest.getPassword()));
        var candidate = CandidateRequest.render(candidateRequest);
        var candidateResponse = CandidateResponse.parse(candidateService.save(candidate));
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponse);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateResponse> show(@PathVariable("candidateId") UUID candidateId) {
        var candidate = this.candidateService.findById(candidateId);

        return candidate.map(e -> ResponseEntity.ok(CandidateResponse.parse(e)))
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));
    }

    @PutMapping("/{candidateId}")
    public ResponseEntity<Void> update(@PathVariable UUID candidateId,
                                       @RequestBody @Valid CandidateRequest candidateRequest) {
        candidateRequest.setPassword(passwordEncoder.encode(candidateRequest.getPassword()));
        var candidateOptional = this.candidateService.findById(candidateId);

        if (candidateOptional.isPresent()) {
            var candidate = candidateOptional.get();
            candidate.setUsername(candidateRequest.getUsername());
            candidate.setPassword(candidateRequest.getPassword());
            candidate.setEmail(candidateRequest.getEmail());
            this.candidateService.save(candidate);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Void> delete(@PathVariable UUID candidateId) {
        try {
            this.candidateService.deleteById(candidateId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado", ex);
        }
    }
}
