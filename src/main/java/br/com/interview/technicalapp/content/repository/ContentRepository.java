package br.com.interview.technicalapp.content.repository;

import java.util.UUID;

import br.com.interview.technicalapp.content.model.Content;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}
