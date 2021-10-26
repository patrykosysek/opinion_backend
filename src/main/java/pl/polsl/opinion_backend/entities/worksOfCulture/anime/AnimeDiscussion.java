package pl.polsl.opinion_backend.entities.worksOfCulture.anime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
    Set<AnimeDiscussionAnswer> answers = new HashSet<>();

}
