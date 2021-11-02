package pl.polsl.opinion_backend.dtos.discussion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionCreateDTO {

    @NotBlank(message = TOPIC_REQUIRED)
    @Size(max = 100, message = TOPIC_SIZE)
    private String topic;

    @NotBlank(message = TEXT_REQUIRED)
    @Size(max = 300, message = TEXT_SIZE)
    private String text;

}
