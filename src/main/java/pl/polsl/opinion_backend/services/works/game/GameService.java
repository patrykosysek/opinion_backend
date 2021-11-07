package pl.polsl.opinion_backend.services.works.game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.dtos.workOfCulture.WorkOfCultureStatisticResponseDTO;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.workOfCulture.WorkOfCultureType;
import pl.polsl.opinion_backend.repositories.works.game.GameRepository;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.util.NoSuchElementException;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.GAME_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GameService extends WorkOfCultureService<Game, GameRepository> {

    @Override
    public Game getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(GAME_NOT_FOUND));
    }

    public WorkOfCultureStatisticResponseDTO getStatistic(UUID id) {
        Game game = getById(id);
        WorkOfCultureStatisticResponseDTO workOfCultureStatisticResponseDTO = new WorkOfCultureStatisticResponseDTO();
        workOfCultureStatisticResponseDTO.setDiscussionCount(game.getDiscussions().size());
        workOfCultureStatisticResponseDTO.setReviewCount(game.getReviews().size());
        workOfCultureStatisticResponseDTO.setSeenListCount(game.getGameSeenLists().size());
        workOfCultureStatisticResponseDTO.setWatchListCount(game.getGameWatchLists().size());

        workOfCultureStatisticResponseDTO.setId(id);
        workOfCultureStatisticResponseDTO.setImageUrl(game.getImageUrl());
        workOfCultureStatisticResponseDTO.setWorkOfCultureType(WorkOfCultureType.GAME);
        workOfCultureStatisticResponseDTO.setTitle(game.getTitle());


        game.getGenres().forEach(genre -> {
            for (GenreType genreType : GenreType.values()) {
                if (genre.getName().equals(genreType.getName()))
                    workOfCultureStatisticResponseDTO.getGenres().add(genreType);
            }
        });
        return workOfCultureStatisticResponseDTO;
    }


}
