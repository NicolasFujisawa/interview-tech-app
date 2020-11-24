package br.com.interview.technicalapp.candidate.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.interview.technicalapp.content.model.Content;
import br.com.interview.technicalapp.user.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
public class Candidate extends User {

    @ManyToMany
    @JoinTable(
            name = "candidate_content",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private List<Content> availableContents = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate)) return false;
        if (!super.equals(o)) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(availableContents, candidate.availableContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), availableContents);
    }
}
