package pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesDiscussionAnswer extends BasicAuditing {

    @Column(nullable = false, columnDefinition = "CLOB")
    private String text;

    @ManyToOne(optional = false)
    private TvSeriesDiscussion discussion;

}
