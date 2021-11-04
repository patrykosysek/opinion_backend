package pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesSeenList;
import pl.polsl.opinion_backend.entities.list.tvSeries.TvSeriesWatchList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeries extends WorkOfCulture {

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

}
