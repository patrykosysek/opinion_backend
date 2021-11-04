package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;
import pl.polsl.opinion_backend.helpers.Interest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Manga extends WorkOfCulture implements Interest {

    @OneToOne(cascade = CascadeType.ALL)
    private MangaStatistic statistic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AnimeMangaGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manga")
    Set<MangaDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manga")
    Set<MangaReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "manga")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MangaWatchList> mangaWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "manga")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MangaSeenList> mangaSeenLists = new HashSet<>();

    public void addStatistic(MangaStatistic mangaStatistic) {
        this.statistic = mangaStatistic;
        mangaStatistic.setManga(this);
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
