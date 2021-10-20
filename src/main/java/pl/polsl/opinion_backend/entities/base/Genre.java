package pl.polsl.opinion_backend.entities.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.polsl.opinion_backend.entities.base.BasicEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class Genre extends BasicEntity {

    private String apiId;

    @Column(updatable = false, nullable = false, unique = true)
    private String name;

}
