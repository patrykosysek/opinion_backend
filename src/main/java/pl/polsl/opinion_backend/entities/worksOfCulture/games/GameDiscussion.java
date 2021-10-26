package pl.polsl.opinion_backend.entities.worksOfCulture.games;

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
public class GameDiscussion extends BasicAuditing {

    @ManyToOne(optional = false)
    private Game game;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discussion")
    Set<GameDiscussionAnswer> answers = new HashSet<>();

}
