package pl.polsl.opinion_backend.services.works.anime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.discussion.AnswerCreateDTO;
import pl.polsl.opinion_backend.dtos.discussion.DiscussionCreateDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussion;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.AnimeDiscussionAnswer;
import pl.polsl.opinion_backend.repositories.works.anime.AnimeDiscussionRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;

import java.util.NoSuchElementException;
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
        save(animeDiscussion);
    }

    public void addAnswer(UUID discussionId, AnswerCreateDTO dto) {
        AnimeDiscussion animeDiscussion = getById(discussionId);
        AnimeDiscussionAnswer animeDiscussionAnswer = new AnimeDiscussionAnswer();
        animeDiscussionAnswer.setText(dto.getText());
        animeDiscussion.addAnswer(animeDiscussionAnswer);
        save(animeDiscussion);
    }

    public void deleteAllByCreateBy(UUID createBy) {
        repository.deleteAllByCreateBy(createBy);
    }

}
