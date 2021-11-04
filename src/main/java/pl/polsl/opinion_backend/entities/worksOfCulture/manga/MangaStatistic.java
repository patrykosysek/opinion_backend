package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MangaStatistic extends BasicAuditing implements Interest {

    @OneToOne(mappedBy = "statistic")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Manga manga;

    private int monthDiscussion = 0;
    private int weekDiscussion = 0;
    private int currentDiscussion = 0;

    private int monthReview = 0;
    private int weekReview = 0;
    private int currentReview = 0;

    @Override
    public double workOfCultureInterest() {
        int monthlyDiscussionGrow = currentDiscussion - monthDiscussion;
        int monthlyReviewGrow = currentReview - monthReview;

        int weeklyDiscussionGrow = currentDiscussion - weekDiscussion;
        int weeklyReviewGrow = currentReview - weekReview;

        return 0.3 * (0.6 * weeklyDiscussionGrow + 0.2 * monthlyDiscussionGrow + 0.2 * currentDiscussion) + 0.7 * (0.6 * weeklyReviewGrow + 0.2 * monthlyReviewGrow + 0.2 * currentReview);

    }

}
