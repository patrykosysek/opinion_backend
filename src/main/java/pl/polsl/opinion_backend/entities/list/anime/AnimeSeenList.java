package pl.polsl.opinion_backend.entities.list.anime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnimeSeenList extends BasicAuditing {

    @ManyToOne(optional = false)
    private SeenList seenList;

    @ManyToOne(optional = false)
    private Anime anime;

    public void addAnime(Anime anime) {
        this.anime = anime;
        anime.getAnimeSeenLists().add(this);
    }

    public void addSeenList(SeenList seenList) {
        this.seenList = seenList;
        seenList.getAnimeSeenLists().add(this);
    }

}
