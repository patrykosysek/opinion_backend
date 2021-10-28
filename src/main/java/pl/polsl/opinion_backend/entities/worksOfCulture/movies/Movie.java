package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

import lombok.*;
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<SeenList> seenLists = new HashSet<>();

    @PreRemove
    public void preRemove() {
        removeFromSeenList();
        removeFromWatchList();
    }

    private void removeFromWatchList() {
        watchLists.forEach(watchList -> watchList.getMovies().remove(this));
    }

    private void removeFromSeenList() {
        seenLists.forEach(seenList -> seenList.getMovies().remove(this));
    }

}
