package pl.polsl.opinion_backend.entities.worksOfCulture.games;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GameStatistic extends BasicAuditing   {

    @OneToOne(mappedBy = "statistic")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Game game;

    private int monthDiscussion = 0;
    private int weekDiscussion = 0;
    private int currentDiscussion = 0;

    private int monthReview = 0;
    private int weekReview = 0;
    private int currentReview = 0;

}
