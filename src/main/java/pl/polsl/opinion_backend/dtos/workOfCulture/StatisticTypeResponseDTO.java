package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticTypeResponseDTO {

    private WorkOfCultureType workOfCultureType;
    private Set<StatisticGenreResponseDTO> genreStatistic = new HashSet<>();

}
