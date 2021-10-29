package pl.polsl.opinion_backend.repositories.list;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.user.ReviewList;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface ReviewListRepository extends BasicRepository<ReviewList, UUID> {
}
