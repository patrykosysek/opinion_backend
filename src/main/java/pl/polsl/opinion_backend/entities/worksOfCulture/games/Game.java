package pl.polsl.opinion_backend.entities.worksOfCulture.games;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Game extends WorkOfCulture implements Interest {

    @OneToOne(cascade = CascadeType.ALL)
    private GameStatistic statistic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GameGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GameDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GameReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GameWatchList> gameWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GameSeenList> gameSeenLists = new HashSet<>();

    public void addStatistic(GameStatistic gameStatistic) {
        this.statistic = gameStatistic;
        gameStatistic.setGame(this);
    }

    @Override
    public double workOfCultureInterest() {

        int currentDiscussion = statistic.getCurrentDiscussion();
        int currentReview = statistic.getCurrentReview();

        int monthlyDiscussionGrow = currentDiscussion - statistic.getMonthDiscussion();
        int monthlyReviewGrow = currentReview - statistic.getMonthReview();

        int weeklyDiscussionGrow = currentDiscussion - statistic.getWeekDiscussion();
        int weeklyReviewGrow = currentReview - statistic.getWeekReview();

        return 0.3 * (0.6 * weeklyDiscussionGrow + 0.2 * monthlyDiscussionGrow + 0.2 * currentDiscussion) + 0.7 * (0.6 * weeklyReviewGrow + 0.2 * monthlyReviewGrow + 0.2 * currentReview);

    }
}
