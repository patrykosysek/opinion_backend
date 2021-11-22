package pl.polsl.opinion_backend.dtos.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REVIEW_REQUIRED;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.REVIEW_SIZE;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDTO {

    @NotBlank(message = REVIEW_REQUIRED)
    @Size(max = 3000, message = REVIEW_SIZE)
    private String review;

}
