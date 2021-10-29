package pl.polsl.opinion_backend.entities.user;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SeenList extends BasicAuditing {

    @OneToOne(mappedBy = "seenList", optional = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "seenList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeSeenList> animeSeenLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "seenList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MangaSeenList> mangaSeenLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "seenList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MovieSeenList> movieSeenLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "seenList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TvSeriesSeenList> tvSeriesSeenLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "seenList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GameSeenList> gameSeenLists = new HashSet<>();

}