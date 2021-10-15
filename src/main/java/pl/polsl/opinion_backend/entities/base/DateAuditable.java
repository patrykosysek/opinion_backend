package pl.polsl.opinion_backend.entities.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class DateAuditable extends AbstractBaseEntity {

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @CreatedDate
    protected OffsetDateTime createDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @LastModifiedDate
    protected OffsetDateTime modifyDate;

    @CreatedBy
    protected UUID createBy;

    @LastModifiedBy
    protected UUID modifyBy;
}
