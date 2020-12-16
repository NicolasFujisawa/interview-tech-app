package br.com.interview.technicalapp.content.model;

import br.com.interview.technicalapp.candidate.model.Candidate;
import br.com.interview.technicalapp.question.model.Question;
import br.com.interview.technicalapp.recruiter.model.Recruiter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contents")
@Getter
@Setter
public class Content {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title", nullable = false, length = 110)
    private String title;

    @ManyToMany
    @JoinTable(
            name = "content_question",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Recruiter owner;

    @ManyToMany(mappedBy = "availableContents")
    private List<Candidate> candidates = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;
        Content content = (Content) o;
        return id.equals(content.id) &&
                title.equals(content.title) &&
                Objects.equals(questions, content.questions) &&
                Objects.equals(owner, content.owner) &&
                Objects.equals(candidates, content.candidates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, questions, owner, candidates);
    }
}
