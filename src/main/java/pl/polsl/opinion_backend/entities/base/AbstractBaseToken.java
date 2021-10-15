package pl.polsl.opinion_backend.entities.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.polsl.opinion_backend.entities.user.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.OffsetDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractBaseToken extends AbstractBaseEntity {
    private OffsetDateTime expiryDate;
    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Column(nullable = false)
    private UUID token;

    protected AbstractBaseToken(User user) {
        this.user = user;
    }

    public Boolean isExpired() {
        return OffsetDateTime.now().isAfter(expiryDate);
    }

    public String getToken() {
        return this.token.toString();
    }

}
