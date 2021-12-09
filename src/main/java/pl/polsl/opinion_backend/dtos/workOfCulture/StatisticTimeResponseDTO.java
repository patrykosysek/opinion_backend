package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticTimeResponseDTO {

    private int reviewCountBefore;
    private int reviewCountGain;
    private int reviewCountGainPercent;

    private int discussionCountBefore;
    private int discussionCountGain;
    private int discussionCountGainPercent;

    private int watchListCountBefore;
    private int watchListCountGain;
    private int watchListCountGainPercent;

    private int seenListCountBefore;
    private int seenListCountGain;
    private int seenListCountGainPercent;

}
