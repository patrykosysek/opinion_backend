package pl.polsl.opinion_backend.entities.worksOfCulture.games;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "games")
    Set<SeenList> seenLists = new HashSet<>();

}
