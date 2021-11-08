package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.list.anime.AnimeWatchList;
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


    @Override
    public AnimeDiscussion getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(ANIME_DISCUSSION_NOT_FOUND));
    }

    public void addDiscussion(UUID workOfCultureId, DiscussionCreateDTO dto) {
        Anime anime = animeService.getById(workOfCultureId);
        AnimeDiscussion animeDiscussion = new AnimeDiscussion();
        animeDiscussion.addAnime(anime);
        animeDiscussion.setText(dto.getText());
        animeDiscussion.setTopic(dto.getTopic());
        //save(animeDiscussion);
        anime.getStatistic().setCurrentDiscussion(anime.getStatistic().getCurrentDiscussion() + 1);
        animeService.save(anime);
    }

    public void addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        AnimeDiscussion animeDiscussion = getById(discussionId);
        AnimeDiscussionAnswer animeDiscussionAnswer = new AnimeDiscussionAnswer();
        animeDiscussionAnswer.setText(dto.getText());
        animeDiscussion.addAnswer(animeDiscussionAnswer);
        Anime anime = animeDiscussion.getAnime();
        anime.getStatistic().setCurrentDiscussion(anime.getStatistic().getCurrentDiscussion() + 1);
        //save(animeDiscussion);
        animeService.save(anime);
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

}
