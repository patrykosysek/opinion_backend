package pl.polsl.opinion_backend.entities.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class WorkOfCulture extends BasicAuditing {

    @Column(unique = true)
    private String apiId;

    private String title;

    @Column(nullable = false, columnDefinition = "CLOB")
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    private String imageUrl;

}
