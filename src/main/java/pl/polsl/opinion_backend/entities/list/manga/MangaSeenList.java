package pl.polsl.opinion_backend.entities.list.manga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.entities.user.SeenList;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MangaSeenList extends BasicAuditing {

    @ManyToOne(optional = false)
    private SeenList seenList;

    @ManyToOne(optional = false)
    private Manga manga;

    public void addManga(Manga manga) {
        this.manga = manga;
        manga.getMangaSeenLists().add(this);
    }

    public void addSeenList(SeenList seenList) {
        this.seenList = seenList;
        seenList.getMangaSeenLists().add(this);
    }

}
