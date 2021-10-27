package pl.polsl.opinion_backend.entities.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Preference extends BasicAuditing {

    @ManyToOne
    private User user;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private WorkOfCultureType workOfCultureType;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GenreType favouriteGenre;

    @Column(nullable = false)
    private String favouriteTitle;

}
