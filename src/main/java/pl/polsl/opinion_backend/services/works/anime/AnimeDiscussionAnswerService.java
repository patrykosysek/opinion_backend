package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.anime.AnimeDiscussionAnswerRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_DISCUSSION_ANSWER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeDiscussionAnswerService extends BasicService<AnimeDiscussionAnswer, AnimeDiscussionAnswerRepository> {

    @Override
    public AnimeDiscussionAnswer getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_DISCUSSION_ANSWER_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Page<AnimeDiscussionAnswer> findAllByDiscussion(AnimeDiscussion animeDiscussion, Pageable pageable) {
        return repository.findAllByDiscussion(animeDiscussion, pageable);
    }

}
