package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.list.movie.MovieSeenList;
import pl.polsl.opinion_backend.entities.list.movie.MovieWatchList;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends WorkOfCulture implements Interest {

    @OneToOne(cascade = CascadeType.ALL)
    private MovieStatistic statistic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MovieTvSeriesGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieWatchList> movieWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "movie")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MovieSeenList> movieSeenLists = new HashSet<>();

    public void addStatistic(MovieStatistic movieStatistic) {
        this.statistic = movieStatistic;
        movieStatistic.setMovie(this);
    }

    @Override
    public double workOfCultureInterest() {

        int currentDiscussion = statistic.getCurrentDiscussion();
        int currentReview = statistic.getCurrentReview();

        int monthlyDiscussionGrow = currentDiscussion - statistic.getMonthDiscussion();
        int monthlyReviewGrow = currentReview - statistic.getMonthReview();

        int weeklyDiscussionGrow = currentDiscussion - statistic.getWeekDiscussion();
        int weeklyReviewGrow = currentReview - statistic.getWeekReview();

        return 0.3 * (0.6 * weeklyDiscussionGrow + 0.2 * monthlyDiscussionGrow + 0.2 * currentDiscussion) + 0.7 * (0.6 * weeklyReviewGrow + 0.2 * monthlyReviewGrow + 0.2 * currentReview);

    }

}
