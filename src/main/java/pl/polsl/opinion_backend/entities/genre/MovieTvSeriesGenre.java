package pl.polsl.opinion_backend.entities.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.polsl.opinion_backend.entities.base.Genre;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
public class MovieTvSeriesGenre extends Genre {

}
