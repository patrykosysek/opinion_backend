package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOfCultureTimeStatisticResponseDTO {

    private int reviewCountBefore;
    private int discussionCountBefore;
    private int reviewCountGain;
    private int discussionCountGain;

}
