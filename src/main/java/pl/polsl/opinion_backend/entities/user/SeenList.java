package pl.polsl.opinion_backend.entities.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    private User user;

    @ManyToMany
    private Set<Anime> anime = new HashSet<>();

    @ManyToMany
    private Set<Manga> manga = new HashSet<>();

    @ManyToMany
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany
    private Set<TvSeries> tvSeries = new HashSet<>();

    @ManyToMany
    private Set<Game> games = new HashSet<>();
}