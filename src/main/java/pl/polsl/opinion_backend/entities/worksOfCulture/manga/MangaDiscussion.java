package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.*;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MangaDiscussion extends BasicAuditing {

    @ManyToOne(optional = false)
    private Manga manga;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<MangaDiscussionAnswer> answers = new HashSet<>();

    public void addManga(Manga manga) {
        this.manga = manga;
        manga.getDiscussions().add(this);
    }

    public void addAnswer(MangaDiscussionAnswer mangaDiscussionAnswer) {
        this.answers.add(mangaDiscussionAnswer);
        mangaDiscussionAnswer.setDiscussion(this);
    }

}
