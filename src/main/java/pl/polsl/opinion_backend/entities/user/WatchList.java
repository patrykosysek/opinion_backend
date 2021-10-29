package pl.polsl.opinion_backend.entities.user;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WatchList extends BasicAuditing {

    @OneToOne(mappedBy = "watchList", optional = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "watchList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeWatchList> animeWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "watchList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MangaWatchList> mangaWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "watchList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MovieWatchList> movieWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "watchList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TvSeriesWatchList> tvSeriesWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "watchList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GameWatchList> gameWatchLists = new HashSet<>();

}
