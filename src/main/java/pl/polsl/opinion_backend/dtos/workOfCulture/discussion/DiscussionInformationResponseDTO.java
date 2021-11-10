package pl.polsl.opinion_backend.dtos.workOfCulture.discussion;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionInformationResponseDTO {

    private UUID id;
    private String topic;
    private LocalDate createDate;
    private String title;
    private String author;

    Page<AnswerResponseDTO> answers;

}
