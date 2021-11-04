package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class WorkOfCultureResponseDTO {

    private final UUID id;
    private final String title;
    private final String imageUrl;

}
