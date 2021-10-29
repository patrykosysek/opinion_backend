package pl.polsl.opinion_backend.entities.list.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GameSeenList extends BasicAuditing {

    @ManyToOne(optional = false)
    private SeenList seenList;

    @ManyToOne(optional = false)
    private Game game;


    public void addGame(Game game) {
        this.game = game;
        game.getGameSeenLists().add(this);
    }

    public void addSeenList(SeenList seenList) {
        this.seenList = seenList;
        seenList.getGameSeenLists().add(this);
    }

}
