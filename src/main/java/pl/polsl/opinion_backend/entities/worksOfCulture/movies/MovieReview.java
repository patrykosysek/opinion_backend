package pl.polsl.opinion_backend.entities.worksOfCulture.movies;

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
public class MovieReview extends BasicAuditing {

    @ManyToOne(optional = false)
    private Movie movie;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String comment;

    private int likes = 0;

}
