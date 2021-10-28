package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.user.WatchList;

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
    private Set<AnimeDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anime")
    private Set<AnimeReview> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "anime")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "anime")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SeenList> seenLists = new HashSet<>();

    @PreRemove
    public void preRemove() {
        removeFromSeenList();
        removeFromWatchList();
    }

    private void removeFromWatchList() {
        watchLists.forEach(watchList -> watchList.getAnime().remove(this));
    }

    private void removeFromSeenList() {
        seenLists.forEach(seenList -> seenList.getAnime().remove(this));
    }

}
