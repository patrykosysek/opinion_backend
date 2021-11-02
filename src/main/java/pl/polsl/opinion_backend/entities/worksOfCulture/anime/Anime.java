package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.list.anime.AnimeSeenList;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Anime extends WorkOfCulture {

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

}
