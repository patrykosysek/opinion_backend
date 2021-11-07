package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOfCultureStatisticResponseDTO {

    private UUID id;
    private String title;
    private String imageUrl;
    private WorkOfCultureType workOfCultureType;
    private int reviewCount;
    private int discussionCount;
    private int watchListCount;
    private int seenListCount;
    private Set<GenreType> genres = new HashSet<>();

}
