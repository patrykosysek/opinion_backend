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
public class MangaStatistic extends BasicAuditing {

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

}
