package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

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
public class Manga extends WorkOfCulture {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AnimeMangaGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manga")
    Set<MangaDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manga")
    Set<MangaReview> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "manga")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "manga")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<SeenList> seenLists = new HashSet<>();

    @PreRemove
    public void preRemove() {
        removeFromSeenList();
        removeFromWatchList();
    }

    private void removeFromWatchList() {
        watchLists.forEach(watchList -> watchList.getManga().remove(this));
    }

    private void removeFromSeenList() {
        seenLists.forEach(seenList -> seenList.getManga().remove(this));
    }


}
