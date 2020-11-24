package br.com.interview.technicalapp.question.model;


import br.com.interview.technicalapp.content.model.Content;
import br.com.interview.technicalapp.recruiter.model.Recruiter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "questions")
    private List<Content> contents = new ArrayList<>();

    @ManyToMany(mappedBy = "questions")
    private List<Recruiter> recruiters = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return id.equals(question.id) &&
                title.equals(question.title) &&
                Objects.equals(description, question.description) &&
                Objects.equals(contents, question.contents) &&
                Objects.equals(recruiters, question.recruiters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, contents, recruiters);
    }
}
