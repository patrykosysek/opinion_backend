package pl.polsl.opinion_backend.entities.worksOfCulture.games;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.list.game.GameSeenList;
import pl.polsl.opinion_backend.entities.list.game.GameWatchList;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Game extends WorkOfCulture {

    @OneToOne(cascade = CascadeType.ALL)
    private GameStatistic statistic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GameGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    Set<GameDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    Set<GameReview> reviews = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GameWatchList> gameWatchLists = new HashSet<>();

    @OneToMany(orphanRemoval = true, mappedBy = "game")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GameSeenList> gameSeenLists = new HashSet<>();

    public void addStatistic(GameStatistic gameStatistic) {
        this.statistic = gameStatistic;
        gameStatistic.setGame(this);
    }

}
