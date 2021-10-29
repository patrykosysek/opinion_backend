package pl.polsl.opinion_backend.entities.list.tvSeries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesSeenList extends BasicAuditing {

    @ManyToOne(optional = false)
    private SeenList seenList;

    @ManyToOne(optional = false)
    private TvSeries tvSeries;

    public void addTvSeries(TvSeries tvSeries) {
        this.tvSeries = tvSeries;
        tvSeries.getTvSeriesSeenLists().add(this);
    }

    public void addSeenList(SeenList seenList) {
        this.seenList = seenList;
        seenList.getTvSeriesSeenLists().add(this);
    }

}
