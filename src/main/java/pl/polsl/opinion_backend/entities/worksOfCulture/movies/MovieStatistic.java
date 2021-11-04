package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MovieStatistic extends BasicAuditing {

    @OneToOne(mappedBy = "statistic")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Movie movie;

    private int monthDiscussion = 0;
    private int weekDiscussion = 0;
    private int currentDiscussion = 0;

    private int monthReview = 0;
    private int weekReview = 0;
    private int currentReview = 0;

}
