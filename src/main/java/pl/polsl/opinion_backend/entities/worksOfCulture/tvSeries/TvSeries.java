package pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeries extends WorkOfCulture implements Interest {

    @OneToOne(cascade = CascadeType.ALL)
    private TvSeriesStatistic statistic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MovieTvSeriesGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tvSeries")
    Set<TvSeriesDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tvSeries")
    Set<TvSeriesReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "tvSeries")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<TvSeriesWatchList> tvSeriesWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "tvSeries")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<TvSeriesSeenList> tvSeriesSeenLists = new HashSet<>();

    public void addStatistic(TvSeriesStatistic tvSeriesStatistic) {
        this.statistic = tvSeriesStatistic;
        tvSeriesStatistic.setTvSeries(this);
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
