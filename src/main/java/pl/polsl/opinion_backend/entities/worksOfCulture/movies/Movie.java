package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends WorkOfCulture {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MovieTvSeriesGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    Set<MovieDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    Set<MovieReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieWatchList> movieWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieSeenList> movieSeenLists = new HashSet<>();

}
