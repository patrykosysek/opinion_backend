package pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesDiscussion extends BasicAuditing {

    @ManyToOne(optional = false)
    private TvSeries tvSeries;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<TvSeriesDiscussionAnswer> answers = new HashSet<>();

    public void addTvSeries(TvSeries tvSeries) {
        this.tvSeries = tvSeries;
        tvSeries.getDiscussions().add(this);
    }

    public void addAnswer(TvSeriesDiscussionAnswer tvSeriesDiscussionAnswer) {
        this.answers.add(tvSeriesDiscussionAnswer);
        tvSeriesDiscussionAnswer.setDiscussion(this);
    }

}
