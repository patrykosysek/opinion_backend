package pl.polsl.opinion_backend.entities.worksOfCulture.games;

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
public class GameReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private ReviewList reviewList;

    @ManyToOne
    private Game game;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    @OneToMany(mappedBy = "gameReview", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GameReviewLike> gameReviewLikes = new HashSet<>();


    public void addGame(Game game) {
        this.game = game;
        game.getReviews().add(this);
    }

    public void addReviewList(ReviewList reviewList) {
        this.reviewList = reviewList;
        reviewList.getGameReviews().add(this);
    }

    public void addLike(GameReviewLike gameReviewLike) {
        gameReviewLike.setGameReview(this);
        this.gameReviewLikes.add(gameReviewLike);
    }

}
