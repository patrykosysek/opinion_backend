package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

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
public class MovieReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private ReviewList reviewList;

    @ManyToOne(optional = false)
    private Movie movie;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    @OneToMany(mappedBy = "movieReview", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MovieReviewLike> movieReviewLikes = new HashSet<>();

    public void addMovie(Movie movie) {
        this.movie = movie;
        movie.getReviews().add(this);
    }

    public void addReviewList(ReviewList reviewList) {
        this.reviewList = reviewList;
        reviewList.getMovieReviews().add(this);

    }

    public void addLike(MovieReviewLike movieReviewLike) {
        movieReviewLike.setMovieReview(this);
        this.movieReviewLikes.add(movieReviewLike);
    }

}
