package pl.polsl.opinion_backend.mappers.genre;

import org.mapstruct.Mapper;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.enums.genre.AnimeMangaGenreEnum;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    AnimeMangaGenre toAnimeMangaGenre(AnimeMangaGenreEnum animeMangaGenreEnum);

}
