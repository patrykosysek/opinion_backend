package pl.polsl.opinion_backend.services.user;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.opinion_backend.dtos.user.PreferenceCreateDTO;
import pl.polsl.opinion_backend.dtos.user.UserCreateDTO;
import pl.polsl.opinion_backend.dtos.user.UserUpdateDTO;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.mappers.qualifires.UsernameMapping;
import pl.polsl.opinion_backend.mappers.user.UserMapper;
import pl.polsl.opinion_backend.repositories.user.UserRepository;
import pl.polsl.opinion_backend.services.basic.BasicService;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.genre.AnimeMangaGenreService;
import pl.polsl.opinion_backend.services.works.genre.GameGenreService;
import pl.polsl.opinion_backend.services.works.genre.MovieTvSeriesGenreService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.*;

@RequiredArgsConstructor
@Service
public class UserService extends BasicService<User, UserRepository> implements UserDetailsService {
    private final UserMapper userMapper;
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final GameService gameService;
    private final TvSeriesService tvSeriesService;
    private final MovieService movieService;
    private final AnimeMangaGenreService animeMangaGenreService;
    private final MovieTvSeriesGenreService movieTvSeriesGenreService;
    private final GameGenreService gameGenreService;

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() -> new NoSuchElementException(USER_NOT_FOUND));
    }


    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            if (authentication.getPrincipal() instanceof User)
                return (User) authentication.getPrincipal();

        throw new NoSuchElementException(USER_NOT_FOUND);
    }

    public User create(UserCreateDTO dto) {

        for (PreferenceCreateDTO p : dto.getPreferences()) {
            switch (p.getWorkOfCultureType()) {
                case ANIME, MANGA:
                    if (!animeMangaGenreService.existsByName(p.getFavouriteGenre().getName()))
                        throw new IllegalArgumentException(WRONG_PREFERENCE);
                    break;
                case MOVIE, TVSERIES:
                    if (!movieTvSeriesGenreService.existsByName(p.getFavouriteGenre().getName()))
                        throw new IllegalArgumentException(WRONG_PREFERENCE);
                    break;
                case GAME:
                    if (!gameGenreService.existsByName(p.getFavouriteGenre().getName()))
                        throw new IllegalArgumentException(WRONG_PREFERENCE);
                    break;
                default:
                    throw new IllegalArgumentException(WORK_OF_CULTURE_TYPE_REQUIRED);
            }
        }

        User user = userMapper.toUser(dto);
        return this.save(user);
    }

    @Transactional
    public User changeLockStatus(UUID id) {
        User user = getById(id);
        user.setEnabled(!user.isEnabled());
        return user;
    }

    public User update(UUID id, UserUpdateDTO dto) {
        User user = this.getById(id);
        return save(userMapper.updateUser(user, dto));
    }

    public Page<User> findAllFilteredByEmail(String email, Pageable pageable) {
        return repository.findAllByEmailStartsWithIgnoreCase(email, pageable);
    }

    @UsernameMapping
    public String getUsernameByCreateBy(UUID id) {
        return getById(id).getNickname();
    }

}