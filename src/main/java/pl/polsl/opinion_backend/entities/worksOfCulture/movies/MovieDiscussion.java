package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MovieDiscussion extends BasicAuditing {

    @ManyToOne(optional = false)
    private Movie movie;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieDiscussionAnswer> answers = new HashSet<>();

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getDiscussions().add(this);
    }

    public void addAnswer(MovieDiscussionAnswer movieDiscussionAnswer) {
        this.answers.add(movieDiscussionAnswer);
        movieDiscussionAnswer.setDiscussion(this);
    }

}
