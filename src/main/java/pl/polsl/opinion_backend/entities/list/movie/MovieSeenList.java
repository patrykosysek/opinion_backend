package pl.polsl.opinion_backend.entities.list.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MovieSeenList extends BasicAuditing {

    @ManyToOne(optional = false)
    private SeenList seenList;

    @ManyToOne(optional = false)
    private Movie movie;

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getMovieSeenLists().add(this);
    }

    public void addSeenList(SeenList seenList) {
        this.seenList = seenList;
        seenList.getMovieSeenLists().add(this);
    }

}
