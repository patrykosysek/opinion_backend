package pl.polsl.opinion_backend.entities.list.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MovieWatchList extends BasicAuditing {

    @ManyToOne(optional = false)
    private WatchList watchList;

    @ManyToOne(optional = false)
    private Movie movie;

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getMovieWatchLists().add(this);
    }

    public void addWatchList(WatchList watchList) {
        this.watchList = watchList;
        watchList.getMovieWatchLists().add(this);
    }

}
