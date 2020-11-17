package br.com.interview.technicalapp.content.service;

import br.com.interview.technicalapp.content.model.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentService {

    Optional<Content> findById(UUID contentId);

    List<Content> findAll();

    Content save(Content content);

    void deleteById(UUID contentId);
}
