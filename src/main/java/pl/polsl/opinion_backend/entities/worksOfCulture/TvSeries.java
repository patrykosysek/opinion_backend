package pl.polsl.opinion_backend.entities.worksOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.WorkOfCulture;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TvSeries extends WorkOfCulture {

    @ManyToMany
    private Set<MovieTvSeriesGenre> genres = new HashSet<>();
}
