package pl.polsl.opinion_backend.entities.worksOfCulture.games;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
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
public class Game extends WorkOfCulture {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GameGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    Set<GameDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    Set<GameReview> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "games")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "games")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<SeenList> seenLists = new HashSet<>();

    @PreRemove
    public void preRemove() {
        removeFromSeenList();
        removeFromWatchList();
    }

    private void removeFromWatchList() {
        watchLists.forEach(watchList -> watchList.getGames().remove(this));
    }

    private void removeFromSeenList() {
        seenLists.forEach(seenList -> seenList.getGames().remove(this));
    }


}
