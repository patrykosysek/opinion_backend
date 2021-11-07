package pl.polsl.opinion_backend.repositories.works.tvSeries;

import org.springframework.stereotype.Repository;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeriesDiscussion;
import pl.polsl.opinion_backend.repositories.base.BasicRepository;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TvSeriesDiscussionRepository extends BasicRepository<TvSeriesDiscussion, UUID> {

    void deleteAllByCreateBy(UUID createBy);

    Set<TvSeriesDiscussion> findAllByTvSeries_IdAndCreateDateIsAfterAndCreateDateIsBefore(UUID id, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesDiscussion> findAllByTvSeries_IdAndCreateDateIsBefore(UUID id, OffsetDateTime date);

    Set<TvSeriesDiscussion> findAllByTvSeriesGenresAndCreateDateIsAfterAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime startDate, OffsetDateTime endDate);

    Set<TvSeriesDiscussion> findAllByTvSeriesGenresAndCreateDateIsBefore(MovieTvSeriesGenre movieTvSeriesGenre, OffsetDateTime date);

}
