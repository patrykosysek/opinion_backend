package pl.polsl.opinion_backend.entities.user;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.GameReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieReview;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesReview;

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
public class ReviewList extends BasicAuditing {

    @OneToOne(mappedBy = "reviewList", optional = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(orphanRemoval = true, mappedBy = "reviewList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeReview> animeReviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "reviewList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MangaReview> mangaReviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "reviewList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MovieReview> movieReviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "reviewList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TvSeriesReview> tvSeriesReviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "reviewList")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GameReview> gameReviews = new HashSet<>();

}
