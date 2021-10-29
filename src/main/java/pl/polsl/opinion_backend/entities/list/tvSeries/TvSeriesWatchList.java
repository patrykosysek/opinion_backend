package pl.polsl.opinion_backend.entities.list.tvSeries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesWatchList extends BasicAuditing {

    @ManyToOne(optional = false)
    private WatchList watchList;

    @ManyToOne(optional = false)
    private TvSeries tvSeries;

    public void addTvSeries(TvSeries tvSeries) {
        this.tvSeries = tvSeries;
        tvSeries.getTvSeriesWatchLists().add(this);
    }

    public void addWatchList(WatchList watchList) {
        this.watchList = watchList;
        watchList.getTvSeriesWatchLists().add(this);
    }

}
