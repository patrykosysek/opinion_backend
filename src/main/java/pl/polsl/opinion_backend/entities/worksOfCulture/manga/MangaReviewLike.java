package pl.polsl.opinion_backend.entities.worksOfCulture.manga;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.polsl.opinion_backend.entities.base.BasicAuditing;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MangaReviewLike extends BasicAuditing {

    private boolean positive;

    @ManyToOne
    private MangaReview mangaReview;

    @PreRemove
    private void preRemove() {
        mangaReview.getMangaReviewLikes().remove(this);
    }

}
