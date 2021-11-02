package pl.polsl.opinion_backend.repositories.works.movie;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.MovieDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.util.UUID;

@Repository
public interface MovieDiscussionRepository extends BasicRepository<MovieDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);


}
