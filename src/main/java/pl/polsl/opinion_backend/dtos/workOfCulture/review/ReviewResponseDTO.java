package pl.polsl.opinion_backend.dtos.workOfCulture.review;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    private String title;

    private String author;

    private String comment;

    private int likes = 0;

    private LocalDate createDate;

}
