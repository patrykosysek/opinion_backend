package pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.ReviewList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeriesReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private ReviewList reviewList;

    @ManyToOne(optional = false)
    private TvSeries tvSeries;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    private int likes = 0;

    public void addTvSeries(TvSeries tvSeries) {
        this.tvSeries = tvSeries;
        tvSeries.getReviews().add(this);
    }

    public void addReviewList(ReviewList reviewList) {
        this.reviewList = reviewList;
        reviewList.getTvSeriesReviews().add(this);
    }

}
