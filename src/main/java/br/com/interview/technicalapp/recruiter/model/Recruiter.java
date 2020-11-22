package br.com.interview.technicalapp.recruiter.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.interview.technicalapp.content.model.Content;
import br.com.interview.technicalapp.question.model.Question;
import br.com.interview.technicalapp.user.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recruiters")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class Recruiter extends User {

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Content> contents = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "recruiter_question",
            joinColumns = @JoinColumn(name = "recruiter_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recruiter)) return false;
        if (!super.equals(o)) return false;
        Recruiter recruiter = (Recruiter) o;
        return Objects.equals(contents, recruiter.contents) &&
                Objects.equals(questions, recruiter.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), contents, questions);
    }
}
