package pl.polsl.opinion_backend.dtos.workOfCulture.discussion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponseDTO {

    private UUID id;

    private String author;

    private String text;

    private LocalDate createDate;

}
