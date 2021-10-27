package pl.polsl.opinion_backend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@Data
@AllArgsConstructor
public class PreferenceCreateDTO {

    @NotNull(message = WORK_OF_CULTURE_TYPE_REQUIRED)
    private WorkOfCultureType workOfCultureType;

    @NotNull(message = FAVOURITE_GENRE_REQUIRED)
    private GenreType favouriteGenre;

    @NotBlank(message = FAVOURITE_TITLE_REQUIRED)
    private String favouriteTitle;

}
