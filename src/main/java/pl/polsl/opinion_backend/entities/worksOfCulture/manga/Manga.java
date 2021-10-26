package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "manga")
    Set<SeenList> seenLists = new HashSet<>();

}
