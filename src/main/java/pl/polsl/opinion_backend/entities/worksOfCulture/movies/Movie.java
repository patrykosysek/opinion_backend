package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.user.WatchList;

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

    @ManyToMany(mappedBy = "movies")
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    Set<SeenList> seenLists = new HashSet<>();

}
