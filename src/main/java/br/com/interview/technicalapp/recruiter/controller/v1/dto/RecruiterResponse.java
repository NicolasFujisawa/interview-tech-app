package br.com.interview.technicalapp.recruiter.controller.v1.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.interview.technicalapp.recruiter.controller.v1.RecruiterController;
import br.com.interview.technicalapp.recruiter.model.Recruiter;

import java.util.Objects;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
public class RecruiterResponse extends RepresentationModel<RecruiterResponse> {

    private UUID id;

    private String username;

    private String email;

    public static RecruiterResponse render(Recruiter recruiter) {
        var recruiterResponse = new RecruiterResponse();
        recruiterResponse.setId(recruiter.getId());
        recruiterResponse.setUsername(recruiter.getUsername());
        recruiterResponse.setEmail(recruiter.getEmail());
        recruiterResponse.add(linkTo(methodOn(RecruiterController.class).show(recruiterResponse.getId())).withSelfRel());
        recruiterResponse.add(linkTo(methodOn(RecruiterController.class).listRecruiterQuestions(recruiterResponse.getId()))
                .withRel("questions"));
        recruiterResponse.add(linkTo(methodOn(RecruiterController.class).listRecruiterContents(recruiterResponse.getId()))
                .withRel("contents"));

        return recruiterResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecruiterResponse)) return false;
        if (!super.equals(o)) return false;
        RecruiterResponse that = (RecruiterResponse) o;
        return id.equals(that.id) &&
                username.equals(that.username) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, username, email);
    }
}
