package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.workOfCulture.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.anime.AnimeDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.ANIME_DISCUSSION_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AnimeDiscussionService extends BasicService<AnimeDiscussion, AnimeDiscussionRepository> {
    private final AnimeService animeService;
    private final AnimeDiscussionAnswerService animeDiscussionAnswerService;

    @Override
    public AnimeDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_DISCUSSION_NOT_FOUND));
    }

    public AnimeDiscussion addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Anime anime = animeService.getById(workOfCultureId);
        AnimeDiscussion animeDiscussion = new AnimeDiscussion();
        animeDiscussion.addAnime(anime);
        animeDiscussion.setText(dto.getText());
        animeDiscussion.setTopic(dto.getTopic());
        anime.getStatistic().setCurrentDiscussion(anime.getStatistic().getCurrentDiscussion() + 1);
        return save(animeDiscussion);
    }

    public AnimeDiscussionAnswer addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        AnimeDiscussion animeDiscussion = getById(discussionId);
        AnimeDiscussionAnswer animeDiscussionAnswer = new AnimeDiscussionAnswer();
        animeDiscussionAnswer.setText(dto.getText());
        animeDiscussion.addAnswer(animeDiscussionAnswer);
        Anime anime = animeDiscussion.getAnime();
        anime.getStatistic().setCurrentDiscussion(anime.getStatistic().getCurrentDiscussion() + 1);
        animeService.save(anime);
        return animeDiscussionAnswerService.save(animeDiscussionAnswer);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

    public Set<AnimeDiscussion> findAllByAnimeIdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnime_IdAndCreateDateIsAfterAndCreateDateIsBefore(id, startDate, endDate);
    }

    public Set<AnimeDiscussion> findAllByAnimeIdAndCreateDateIsBefore(UUID id, OffsetDateTime date) {
        return repository.findAllByAnime_IdAndCreateDateIsBefore(id, date);
    }

    public Set<AnimeDiscussion> findAllByAnimeGenresAndCreateDateIsAfterAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime startDate, OffsetDateTime endDate) {
        return repository.findAllByAnimeGenresNameAndCreateDateIsAfterAndCreateDateIsBefore(animeMangaGenre, startDate, endDate);
    }

    public Set<AnimeDiscussion> findAllByAnimeGenresAndCreateDateIsBefore(String animeMangaGenre, OffsetDateTime date) {
        return repository.findAllByAnimeGenresNameAndCreateDateIsBefore(animeMangaGenre, date);
    }

    public Set<AnimeDiscussion> findAllByGenresName(String genre) {
        return repository.findAllByAnimeGenresName(genre);
    }

    public Page<AnimeDiscussion> findAllByCreateBy(UUID id, Pageable pageable) {
        return repository.findAllByCreateBy(id, pageable);
    }

    public Page<AnimeDiscussion> findAllByCreateByOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateAsc(id, pageable);
    }

    public Page<AnimeDiscussion> findAllByCreateByOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByCreateByOrderByCreateDateDesc(id, pageable);
    }

    public Page<AnimeDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<AnimeDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<AnimeDiscussion> findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByCreateByAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

    public Page<AnimeDiscussion> findAllByAnimeId(UUID id, Pageable pageable) {
        return repository.findAllByAnime_Id(id, pageable);
    }

    public Page<AnimeDiscussion> findAllByAnimeIdOrderByCreateDateAsc(UUID id, Pageable pageable) {
        return repository.findAllByAnime_IdOrderByCreateDateAsc(id, pageable);
    }

    public Page<AnimeDiscussion> findAllByAnimeIdOrderByCreateDateDesc(UUID id, Pageable pageable) {
        return repository.findAllByAnime_IdOrderByCreateDateDesc(id, pageable);
    }

    public Page<AnimeDiscussion> findAllByAnimeIdAndTopicStartingWithIgnoreCase(UUID id, String topic, Pageable pageable) {
        return repository.findAllByAnime_IdAndTopicStartingWithIgnoreCase(id, topic, pageable);
    }

    public Page<AnimeDiscussion> findAllByAnimeIdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByAnime_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateAsc(id, topic, pageable);
    }

    public Page<AnimeDiscussion> findAllByAnimeIdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(UUID id, String topic, Pageable pageable) {
        return repository.findAllByAnime_IdAndTopicStartingWithIgnoreCaseOrderByCreateDateDesc(id, topic, pageable);
    }

}
