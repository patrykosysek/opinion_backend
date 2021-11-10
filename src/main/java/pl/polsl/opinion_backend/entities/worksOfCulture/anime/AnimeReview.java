package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

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
public class AnimeReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private ReviewList reviewList;

    @ManyToOne(optional = false)
    private Anime anime;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    @OneToMany(mappedBy = "animeReview", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeReviewLike> animeReviewLikes = new HashSet<>();


    public void addAnime(Anime anime) {
        this.anime = anime;
        anime.getReviews().add(this);
    }

    public void addReviewList(ReviewList reviewList) {
        this.reviewList = reviewList;
        reviewList.getAnimeReviews().add(this);
    }

    public void addLike(AnimeReviewLike animeReviewLike) {
        animeReviewLike.setAnimeReview(this);
        this.animeReviewLikes.add(animeReviewLike);
    }

}
