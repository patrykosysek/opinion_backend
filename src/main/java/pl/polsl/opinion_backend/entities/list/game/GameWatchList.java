package pl.polsl.opinion_backend.entities.list.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GameWatchList extends BasicAuditing {

    @ManyToOne(optional = false)
    private WatchList watchList;

    @ManyToOne(optional = false)
    private Game game;

    public void addGame(Game game) {
        this.game = game;
        game.getGameWatchLists().add(this);
    }

    public void addWatchList(WatchList watchList) {
        this.watchList = watchList;
        watchList.getGameWatchLists().add(this);
    }

}
