package pl.polsl.opinion_backend.services.works.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.manga.MangaDiscussionAnswerRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_DISCUSSION_ANSWER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaDiscussionAnswerService extends BasicService<MangaDiscussionAnswer, MangaDiscussionAnswerRepository> {

    @Override
    public MangaDiscussionAnswer getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_DISCUSSION_ANSWER_NOT_FOUND));
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Page<MangaDiscussionAnswer> findAllByDiscussion(MangaDiscussion mangaDiscussion, Pageable pageable) {
        return repository.findAllByDiscussion(mangaDiscussion, pageable);
    }

}
