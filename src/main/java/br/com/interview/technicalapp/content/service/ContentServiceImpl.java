package br.com.interview.technicalapp.content.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.interview.technicalapp.content.model.Content;

import br.com.interview.technicalapp.content.repository.ContentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Optional<Content> findById(UUID contentId) {
        return this.contentRepository.findById(contentId);
    }

    @Override
    public List<Content> findAll() {
        return this.contentRepository.findAll();
    }

    @Override
    public Content save(Content content) {
        return this.contentRepository.save(content);
    }

    @Override
    public void deleteById(UUID contentId) {
        this.contentRepository.deleteById(contentId);
    }
}
