package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Anime extends WorkOfCulture implements Interest {

    @OneToOne(cascade = CascadeType.ALL)
    private AnimeStatistic statistic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AnimeMangaGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anime")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anime")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "anime")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeWatchList> animeWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "anime")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AnimeSeenList> animeSeenLists = new HashSet<>();

    public void addStatistic(AnimeStatistic animeStatistic) {
        this.statistic = animeStatistic;
        animeStatistic.setAnime(this);
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
