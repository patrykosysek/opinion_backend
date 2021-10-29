package pl.polsl.opinion_backend.entities.list.manga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.WatchList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MangaWatchList extends BasicAuditing {

    @ManyToOne(optional = false)
    private WatchList watchList;

    @ManyToOne(optional = false)
    private Manga manga;

    public void addManga(Manga manga) {
        this.manga = manga;
        manga.getMangaWatchLists().add(this);
    }

    public void addWatchList(WatchList watchList) {
        this.watchList = watchList;
        watchList.getMangaWatchLists().add(this);
    }

}
