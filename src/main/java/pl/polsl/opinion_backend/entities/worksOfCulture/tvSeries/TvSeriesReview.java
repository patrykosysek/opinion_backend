package pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.ReviewList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "tvSeriesReview", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<TvSeriesReviewLike> tvSeriesReviewLikes = new HashSet<>();

    public void addTvSeries(TvSeries tvSeries) {
        this.tvSeries = tvSeries;
        tvSeries.getReviews().add(this);
    }

    public void addReviewList(ReviewList reviewList) {
        this.reviewList = reviewList;
        reviewList.getTvSeriesReviews().add(this);
    }

    public void addLike(TvSeriesReviewLike tvSeriesReviewLike) {
        tvSeriesReviewLike.setTvSeriesReview(this);
        this.tvSeriesReviewLikes.add(tvSeriesReviewLike);
    }

}
