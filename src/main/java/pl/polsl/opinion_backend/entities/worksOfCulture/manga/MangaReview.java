package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

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
public class MangaReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private ReviewList reviewList;

    @ManyToOne(optional = false)
    private Manga manga;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    @OneToMany(mappedBy = "mangaReview", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<MangaReviewLike> mangaReviewLikes = new HashSet<>();

    public void addManga(Manga manga) {
        this.manga = manga;
        manga.getReviews().add(this);
    }

    public void addReviewList(ReviewList reviewList) {
        this.reviewList = reviewList;
        reviewList.getMangaReviews().add(this);
    }

    public void addLike(MangaReviewLike mangaReviewLike) {
        mangaReviewLike.setMangaReview(this);
        this.mangaReviewLikes.add(mangaReviewLike);
    }

}
