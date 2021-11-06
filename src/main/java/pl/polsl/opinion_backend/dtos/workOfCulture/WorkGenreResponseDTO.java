package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import java.util.Set;

@Getter
@AllArgsConstructor
public class WorkGenreResponseDTO {

    private final WorkOfCultureType workOfCultureType;
    private final Set<GenreType> genreTypes;

}
