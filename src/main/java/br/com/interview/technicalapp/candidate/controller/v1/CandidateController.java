package br.com.interview.technicalapp.candidate.controller.v1;

import br.com.interview.technicalapp.candidate.controller.v1.dto.CandidateRequest;
import br.com.interview.technicalapp.candidate.controller.v1.dto.CandidateResponse;
import br.com.interview.technicalapp.candidate.service.CandidateService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/v1/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> list() {
        var candidates = this.candidateService.findAll();

        return ResponseEntity.ok(candidates
                .stream()
                .map(CandidateResponse::render)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CandidateResponse> create(@RequestBody CandidateRequest candidateRequest) {
        var candidate = CandidateRequest.render(candidateRequest);
        var candidateResponse = CandidateResponse.render(this.candidateService.save(candidate));

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateResponse);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateResponse> show(@PathVariable UUID candidateId) {
        var candidate = this.candidateService.findById(candidateId);

        return candidate.map(e -> ResponseEntity.ok(CandidateResponse.render(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{candidateId}")
    public ResponseEntity<Void> update(@PathVariable UUID candidateId,
                                       @RequestBody CandidateRequest candidateRequest) {
        var candidateOptional = this.candidateService.findById(candidateId);

        if (candidateOptional.isPresent()) {
            var candidate = candidateOptional.get();
            candidate.setUsername(candidateRequest.getUsername());
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
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidato não encontrado");
        }
    }
}
