package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

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
public class Anime extends WorkOfCulture {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AnimeMangaGenre> genres = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anime")
    Set<AnimeDiscussion> discussions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anime")
    Set<AnimeReview> reviews = new HashSet<>();

    @ManyToMany(mappedBy = "anime")
    Set<WatchList> watchLists = new HashSet<>();

    @ManyToMany(mappedBy = "anime")
    Set<SeenList> seenLists = new HashSet<>();

}
