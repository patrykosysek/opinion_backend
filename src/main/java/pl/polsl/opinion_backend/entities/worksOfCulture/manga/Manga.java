package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.manga.MangaSeenList;
import pl.polsl.opinion_backend.entities.list.manga.MangaWatchList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Manga extends WorkOfCulture {

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

}
