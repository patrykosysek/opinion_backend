package pl.polsl.opinion_backend.entities.user;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Anime> anime = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Manga> manga = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TvSeries> tvSeries = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> games = new HashSet<>();

}
