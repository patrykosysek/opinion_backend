package pl.polsl.opinion_backend.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkGenreResponseDTO;

import java.util.Set;

@Getter
@AllArgsConstructor
public class RegisterResponseDTO {
    private final Set<WorkGenreResponseDTO> workGenres;
}
