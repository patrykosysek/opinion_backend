package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

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
public class AnimeDiscussion extends BasicAuditing {
    @ManyToOne(optional = false)
    private Anime anime;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<AnimeDiscussionAnswer> answers = new HashSet<>();

    public void addAnime(Anime anime) {
        this.anime = anime;
        anime.getDiscussions().add(this);
    }

    public void addAnswer(AnimeDiscussionAnswer animeDiscussionAnswer) {
        this.answers.add(animeDiscussionAnswer);
        animeDiscussionAnswer.setDiscussion(this);
    }

}
