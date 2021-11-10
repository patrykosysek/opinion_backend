package pl.polsl.opinion_backend.services.works.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.MangaDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.manga.MangaDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.MANGA_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MangaDiscussionService extends BasicService<MangaDiscussion, MangaDiscussionRepository> {
    private final MangaService mangaService;
    private final MangaDiscussionAnswerService mangaDiscussionAnswerService;

    @Override
    public MangaDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(MANGA_DISCUSSION_NOT_FOUND));
    }

    public MangaDiscussion addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Manga manga = mangaService.getById(workOfCultureId);
        MangaDiscussion mangaDiscussion = new MangaDiscussion();
        mangaDiscussion.addManga(manga);
        mangaDiscussion.setText(dto.getText());
        mangaDiscussion.setTopic(dto.getTopic());
        manga.getStatistic().setCurrentDiscussion(manga.getStatistic().getCurrentDiscussion() + 1);
        mangaService.save(manga);
        return save(mangaDiscussion);
    }

    public MangaDiscussionAnswer addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        MangaDiscussion mangaDiscussion = getById(discussionId);
        MangaDiscussionAnswer mangaDiscussionAnswer = new MangaDiscussionAnswer();
        mangaDiscussionAnswer.setText(dto.getText());
        mangaDiscussion.addAnswer(mangaDiscussionAnswer);
        Manga manga = mangaDiscussion.getManga();
        manga.getStatistic().setCurrentDiscussion(manga.getStatistic().getCurrentDiscussion() + 1);
        mangaService.save(manga);
        return mangaDiscussionAnswerService.save(mangaDiscussionAnswer);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Set<MangaDiscussion> findAllByMangaIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByManga_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<MangaDiscussion> findAllByMangaIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByManga_IdAndCreateDateIsBefore(id, date);
    }

    public Set<MangaDiscussion> findAllByMangaGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByMangaGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<MangaDiscussion> findAllByMangaGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByMangaGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<MangaDiscussion> findAllByGenresName(String genre) {
        return repository.findAllByMangaGenresName(genre);
    }

    public Page<MangaDiscussion> findAllByCreateBy(UUID id, Pageable pageable) {
        return repository.findAllByCreateBy(id, pageable);
    }

    public Page<MangaDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateAsc(id, pageable);
    }

    public Page<MangaDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateDesc(id, pageable);
    }

    public Page<MangaDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<MangaDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<MangaDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

    public Page<MangaDiscussion> findAllByMangaId(UUID id, Pageable pageable) {
        return repository.findAllByManga_Id(id, pageable);
    }

    public Page<MangaDiscussion> findAllByMangaIdOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByManga_IdOrderByCreateDateAsc(id, pageable);
    }

    public Page<MangaDiscussion> findAllByMangaIdOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByManga_IdOrderByCreateDateDesc(id, pageable);
    }

    public Page<MangaDiscussion> findAllByMangaIdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByManga_IdAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<MangaDiscussion> findAllByMangaIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByManga_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<MangaDiscussion> findAllByMangaIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByManga_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }


}
