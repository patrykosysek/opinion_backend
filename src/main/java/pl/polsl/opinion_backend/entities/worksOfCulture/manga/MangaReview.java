package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MangaReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private Manga manga;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    private int likes = 0;

}
