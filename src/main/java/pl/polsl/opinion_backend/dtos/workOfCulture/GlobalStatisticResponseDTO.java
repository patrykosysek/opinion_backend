package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalStatisticResponseDTO {

    private Set<StatisticTypeResponseDTO> statistic = new HashSet<>();

}
