package pl.polsl.opinion_backend.services.works.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.manga.MangaDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaDiscussionService extends BasicService<MangaDiscussion, MangaDiscussionRepository> {
    private final MangaService mangaService;

    @Override
    public MangaDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_DISCUSSION_NOT_FOUND));
    }

    public void addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Manga manga = mangaService.getById(workOfCultureId);
        MangaDiscussion mangaDiscussion = new MangaDiscussion();
        mangaDiscussion.addManga(manga);
        mangaDiscussion.setText(dto.getText());
        mangaDiscussion.setTopic(dto.getTopic());
        save(mangaDiscussion);
    }

    public void addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        MangaDiscussion mangaDiscussion = getById(discussionId);
        MangaDiscussionAnswer mangaDiscussionAnswer = new MangaDiscussionAnswer();
        mangaDiscussionAnswer.setText(dto.getText());
        mangaDiscussion.addAnswer(mangaDiscussionAnswer);
        save(mangaDiscussion);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

}
