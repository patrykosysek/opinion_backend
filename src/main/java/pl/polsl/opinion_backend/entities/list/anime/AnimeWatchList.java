package pl.polsl.opinion_backend.entities.list.anime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnimeWatchList extends BasicAuditing {

    @ManyToOne(optional = false)
    private WatchList watchList;

    @ManyToOne(optional = false)
    private Anime anime;

    public void addAnime(Anime anime) {
        this.anime = anime;
        anime.getAnimeWatchLists().add(this);
    }

    public void addWatchList(WatchList watchList) {
        this.watchList = watchList;
        watchList.getAnimeWatchLists().add(this);
    }

}
