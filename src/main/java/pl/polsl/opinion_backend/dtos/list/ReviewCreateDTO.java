package pl.polsl.opinion_backend.dtos.list;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REVIEW_REQUIRED;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REVIEW_SIZE;

@Data
@AllArgsConstructor
public class ReviewCreateDTO {

    @NotBlank(message = REVIEW_REQUIRED)
    @Size(max = 200, message = REVIEW_SIZE)
    private String review;

}
