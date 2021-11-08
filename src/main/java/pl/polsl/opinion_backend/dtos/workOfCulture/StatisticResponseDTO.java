package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponseDTO {

    private int reviewCount;
    private int discussionCount;
    private int watchListCount;
    private int seenListCount;

}
