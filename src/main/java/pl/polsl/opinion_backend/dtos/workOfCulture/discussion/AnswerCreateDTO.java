package pl.polsl.opinion_backend.dtos.workOfCulture.discussion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TEXT_REQUIRED;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.TEXT_SIZE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreateDTO {

    @NotBlank(message = TEXT_REQUIRED)
    @Size(max = 200, message = TEXT_SIZE)
    private String text;

}

