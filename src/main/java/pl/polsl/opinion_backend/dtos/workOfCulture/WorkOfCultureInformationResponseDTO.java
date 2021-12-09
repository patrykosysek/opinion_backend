package pl.polsl.opinion_backend.dtos.workOfCulture;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkOfCultureInformationResponseDTO {

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String imageUrl;
    Set<GenreType> genres = new HashSet<>();
    WorkOfCultureType workOfCultureType;

}
