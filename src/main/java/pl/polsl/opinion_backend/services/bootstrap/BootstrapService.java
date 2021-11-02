package pl.polsl.opinion_backend.services.bootstrap;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.bootstrap.BootstrapStatus;
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.entities.worksOfCulture.anime.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.games.Game;
import pl.polsl.opinion_backend.entities.worksOfCulture.manga.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.movies.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.tvSeries.TvSeries;
import pl.polsl.opinion_backend.enums.genre.AnimeMangaGenreEnum;
import pl.polsl.opinion_backend.enums.genre.GenreType;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;
import pl.polsl.opinion_backend.mappers.genre.GenreMapper;
import pl.polsl.opinion_backend.services.role.RoleGroupService;
import pl.polsl.opinion_backend.services.user.UserService;
import pl.polsl.opinion_backend.services.works.anime.AnimeService;
import pl.polsl.opinion_backend.services.works.game.GameService;
import pl.polsl.opinion_backend.services.works.genre.AnimeMangaGenreService;
import pl.polsl.opinion_backend.services.works.genre.GameGenreService;
import pl.polsl.opinion_backend.services.works.genre.GenreService;
import pl.polsl.opinion_backend.services.works.genre.MovieTvSeriesGenreService;
import pl.polsl.opinion_backend.services.works.manga.MangaService;
import pl.polsl.opinion_backend.services.works.movie.MovieService;
import pl.polsl.opinion_backend.services.works.tvSeries.TvSeriesService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BootstrapService {

    private final PasswordEncoder passwordEncoder;
    private final RoleGroupService roleGroupService;
    private final UserService userService;
    private final BootstrapStatusService bootstrapStatusService;

    // Genre
    private final GenreService genreService;
    private final GenreMapper genreMapper;
    private final AnimeMangaGenreService animeMangaGenreService;
    private final MovieTvSeriesGenreService movieTvSeriesGenreService;
    private final GameGenreService gameGenreService;

    // Work of culture services
    private final AnimeService animeService;
    private final MangaService mangaService;
    private final MovieService movieService;
    private final TvSeriesService tvSeriesService;
    private final GameService gameService;

    public void setup() {
        if (bootstrapStatusService.isDone()) {
            log.info("Bootstrap already done");
        } else {
            try {
                createRoleGroups();
                createDefaultAdmin();
                createDefaultUser();
                getGenreTypes();
                animeGenerating();
//                mangaGenerating();
//                movieGenerating();
//                tvSeriesGenerating();
                //gameGenerating();
                bootstrapStatusService.save(new BootstrapStatus(true));
            } catch (Exception e) {
                log.info(e.getMessage());
                log.info("Bootstrap failed");
            }

        }
    }

    // DEFAULT USERS

    public void createRoleGroups() {
        Arrays.stream(RoleGroupEnum.values()).forEach(roleGroupName -> {
            RoleGroup roleGroup = new RoleGroup(roleGroupName.name());
            roleGroupService.save(roleGroup);
        });
    }

    public void createDefaultAdmin() {
        User user = new User(
                "admin@onet.pl",
                passwordEncoder.encode("ADMIN"),
                true,
                40,
                GenreType.COMEDY,
                "Adminos"
        );
        user.getRoleGroups().add(roleGroupService.getByRoleName("ADMIN"));
        userService.save(user);
    }

    public void createDefaultUser() {
        User user = new User(
                "opinion_user@onet.pl",
                passwordEncoder.encode("OPINION_USER"),
                true,
                22,
                GenreType.FANTASY,
                "Patros"
        );
        user.getRoleGroups().add(roleGroupService.getByRoleName("OPINION_USER"));
        userService.save(user);
    }

    // DEFAULT GENRE TYPES

    public void getGenreTypes() throws IOException {
        generateMovieGeneres();
        generateAnimeGeneres();
        generateGameGeneres();
    }

    public void generateMovieGeneres() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://data-imdb1.p.rapidapi.com/genres/")
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "dab265cb09msha3edaa1ac59915dp1e92ccjsnb9a97ec0d817")
                .build();

        Response response = client.newCall(request).execute();

        if (response.body() == null)
            throw new IllegalArgumentException("RESPONSE_BODY_NOT_FOUND");

        String body = response.body().string();

        JSONObject root = new JSONObject(body);
        JSONArray result = root.getJSONArray("results");

        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonGenre = result.getJSONObject(i);
            MovieTvSeriesGenre genre = new MovieTvSeriesGenre();
            genre.setName(jsonGenre.getString("genre"));

            genreService.save(genre);
        }

    }

    public void generateAnimeGeneres() {
        EnumSet.allOf(AnimeMangaGenreEnum.class).forEach(genre -> {
            genreService.save(genreMapper.toAnimeMangaGenre(genre));
        });
    }

    public void generateGameGeneres() throws IOException {

        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url("https://api.rawg.io/api/genres?key=a63115890c184d4aaa49b2a5ec7fdbfc")
                .get()
                .addHeader("User-Agent", "Opinion")
                .build();


        Response response = client.newCall(request).execute();

        if (response.body() == null)
            throw new IllegalArgumentException("RESPONSE_BODY_NOT_FOUND");

        String body = response.body().string();

        JSONObject root = new JSONObject(body);
        JSONArray result = root.getJSONArray("results");

        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonGenre = result.getJSONObject(i);
            GameGenre genre = new GameGenre();
            genre.setName(jsonGenre.getString("name"));
            genre.setApiId(String.valueOf(jsonGenre.getInt("id")));

            genreService.save(genre);
        }

    }

    // ANIME

    public void animeGenerating() {
        OkHttpClient client = new OkHttpClient();

        List<AnimeMangaGenre> genres = ImmutableList.copyOf(animeMangaGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createAnimeFromGenre(genre, client);
                log.info("Anime generated for genre  " + genre.getName());
            } catch (IOException e) {
                log.info(e.getMessage());
                log.info("Anime creation failed");
            }
        });


    }

    public void createAnimeFromGenre(AnimeMangaGenre genre, OkHttpClient client) throws IOException {

        for (int page = 1; page < 10; page++) {
            String url = "https://jikan1.p.rapidapi.com/search/anime?page=" + page + "&genre=" + genre.getApiId();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                String body = response.body().string();
                JSONObject root = new JSONObject(body);
                JSONArray result = root.getJSONArray("results");

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonAnime = result.getJSONObject(i);
                    Anime anime = new Anime();

                    String title = jsonAnime.getString("title");
                    String apiId = String.valueOf(jsonAnime.getInt("mal_id"));
                    String description = jsonAnime.getString("synopsis");
                    Object date = jsonAnime.get("start_date");
                    String stringDate = date.toString();

                    LocalDate releaseDate = stringDate.equals("null") ? null : OffsetDateTime.parse(stringDate).toLocalDate();
                    String imageUrl = jsonAnime.getString("image_url");

                    if (animeService.existsByApiId(apiId)) {
                        Anime existingAnime = animeService.getByApiId(apiId);
                        existingAnime.getGenres().add(genre);
                        animeService.save(existingAnime);

                    } else if (title != null && description != null && description.isBlank() && releaseDate != null && imageUrl != null) {
                        anime.setTitle(title);
                        anime.setApiId(apiId);
                        anime.setDescription(description);
                        anime.setReleaseDate(releaseDate);
                        anime.setImageUrl(imageUrl);
                        anime.getGenres().add(genre);
                        animeService.save(anime);

                    }
                }
            }
        }
    }

    // MANGA

    public void mangaGenerating() {
        OkHttpClient client = new OkHttpClient();

        List<AnimeMangaGenre> genres = ImmutableList.copyOf(animeMangaGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createMangaFromGenre(genre, client);
                log.info("Manga generated for genre " + genre.getName());
            } catch (IOException e) {
                log.info(e.getMessage());
                log.info("Manga creation failed");
            }
        });

    }

    public void createMangaFromGenre(AnimeMangaGenre genre, OkHttpClient client) throws IOException {


        for (int page = 1; page < 5; page++) {
            String url = "https://jikan1.p.rapidapi.com/search/manga?page=" + page + "&genre=" + genre.getApiId();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                    .build();

            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                String body = response.body().string();

                JSONObject root = new JSONObject(body);
                JSONArray result = root.getJSONArray("results");

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jsonAnime = result.getJSONObject(i);
                    Manga manga = new Manga();

                    String title = jsonAnime.getString("title");
                    String apiId = String.valueOf(jsonAnime.getInt("mal_id"));
                    String description = jsonAnime.getString("synopsis");
                    Object date = jsonAnime.get("start_date");
                    String stringDate = date.toString();

                    LocalDate releaseDate = stringDate.equals("null") ? null : OffsetDateTime.parse(stringDate).toLocalDate();
                    String imageUrl = jsonAnime.getString("image_url");

                    if (mangaService.existsByApiId(apiId)) {
                        Manga existingManga = mangaService.getByApiId(apiId);
                        existingManga.getGenres().add(genre);
                        mangaService.save(existingManga);

                    } else if (title != null && description != null && !description.isBlank() && releaseDate != null && imageUrl != null) {
                        manga.setTitle(title);
                        manga.setApiId(apiId);
                        manga.setDescription(description);
                        manga.setReleaseDate(releaseDate);
                        manga.setImageUrl(imageUrl);
                        manga.getGenres().add(genre);

                        mangaService.save(manga);
                    }
                }
            }
        }
    }

    // MOVIE

    public void movieGenerating() {

        OkHttpClient client = new OkHttpClient();

        List<MovieTvSeriesGenre> genres = ImmutableList.copyOf(movieTvSeriesGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createMovieFromGenre(genre, client);
                log.info("Movie generated for genre " + genre.getName());
            } catch (IOException e) {
                log.info(e.getMessage());
                log.info("Movie creation failed");
            }
        });
    }

    public void createMovieFromGenre(MovieTvSeriesGenre genre, OkHttpClient client) throws IOException {

        String url = "https://data-imdb1.p.rapidapi.com/movie/byGen/" + genre.getName() + "/?page_size=50";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();

        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            String body = response.body().string();
            JSONObject root = new JSONObject(body);
            JSONArray result = root.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonAnime = result.getJSONObject(i);

                String imdbId = jsonAnime.getString("imdb_id");
                createMovieFromImdbId(imdbId, client);
            }
        }
    }

    public void createMovieFromImdbId(String imdb, OkHttpClient client) throws IOException {

        String url = "https://data-imdb1.p.rapidapi.com/movie/id/" + imdb + "/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();

        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            String body = response.body().string();
            JSONObject root = new JSONObject(body);
            JSONObject jsonMovie = root.getJSONObject("results");

            String title = jsonMovie.getString("title");
            String description = jsonMovie.getString("description");
            String imageUrl = jsonMovie.getString("image_url");
            JSONArray generes = jsonMovie.getJSONArray("gen");

            Object release = jsonMovie.get("release");
            String stringDate = release.toString();

            LocalDate releaseDate = stringDate.equals("null") ? null : LocalDate.parse(stringDate);

            Movie movie = new Movie();

            if (title != null && description != null && !description.isBlank() && releaseDate != null && imageUrl != null && !movieService.existsByApiId(imdb)) {
                movie.setTitle(title);
                movie.setApiId(imdb);
                movie.setDescription(description);
                movie.setReleaseDate(releaseDate);
                movie.setImageUrl(imageUrl);

                for (int i = 0; i < generes.length(); i++) {
                    JSONObject jsonGenre = generes.getJSONObject(i);
                    String genreName = jsonGenre.getString("genre");
                    MovieTvSeriesGenre movieTvSeriesGenre = movieTvSeriesGenreService.getByName(genreName);
                    movie.getGenres().add(movieTvSeriesGenre);
                }
                movieService.save(movie);
            }
        }
    }

    // TV SERIES

    public void tvSeriesGenerating() {
        OkHttpClient client = new OkHttpClient();

        List<MovieTvSeriesGenre> genres = ImmutableList.copyOf(movieTvSeriesGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createTvSeriesFromGenre(genre, client);
                log.info("Tv series generated for genre " + genre.getName());
            } catch (IOException e) {
                log.info(e.getMessage());
                log.info("Tv series creation failed");
            }
        });
    }

    public void createTvSeriesFromGenre(MovieTvSeriesGenre genre, OkHttpClient client) throws IOException {

        String url = "https://data-imdb1.p.rapidapi.com/series/byGen/" + genre.getName() + "/?page_size=50";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();

        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            String body = response.body().string();
            JSONObject root = new JSONObject(body);
            JSONArray result = root.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonTvSeries = result.getJSONObject(i);

                String imdbId = jsonTvSeries.getString("imdb_id");
                createTvSeriesFromImdbId(imdbId, client);
            }
        }
    }

    public void createTvSeriesFromImdbId(String imdb, OkHttpClient client) throws IOException {

        String url = "https://data-imdb1.p.rapidapi.com/series/id/" + imdb + "/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "data-imdb1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() == 200) {
            String body = response.body().string();
            JSONObject root = new JSONObject(body);
            JSONObject jsonTvSeries = root.getJSONObject("results");

            String title = jsonTvSeries.getString("title");
            String description = jsonTvSeries.getString("description");
            String imageUrl = jsonTvSeries.getString("image_url");
            JSONArray generes = jsonTvSeries.getJSONArray("gen");

            Object release = jsonTvSeries.get("release");
            String stringDate = release.toString();

            LocalDate releaseDate = stringDate.equals("null") ? null : LocalDate.parse(stringDate);

            TvSeries tvSeries = new TvSeries();

            if (title != null && description != null && !description.isBlank() && releaseDate != null && imageUrl != null && !animeService.existsByTitle(title) && !tvSeriesService.existsByApiId(imdb)) {
                tvSeries.setTitle(title);
                tvSeries.setApiId(imdb);
                tvSeries.setDescription(description);
                tvSeries.setReleaseDate(releaseDate);
                tvSeries.setImageUrl(imageUrl);

                for (int i = 0; i < generes.length(); i++) {
                    JSONObject jsonGenre = generes.getJSONObject(i);
                    String genreName = jsonGenre.getString("genre");
                    MovieTvSeriesGenre movieTvSeriesGenre = movieTvSeriesGenreService.getByName(genreName);
                    tvSeries.getGenres().add(movieTvSeriesGenre);
                }
                tvSeriesService.save(tvSeries);
            }
        }
    }

    // GAMES

    public void gameGenerating() throws IOException {
        OkHttpClient client = new OkHttpClient();

        for (int i = 1; i < 21; i++) {
            String url = "https://api.rawg.io/api/games?key=a63115890c184d4aaa49b2a5ec7fdbfc&page=" + i;
            createGame(client, url);
        }

    }

    public void createGame(OkHttpClient client, String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("User-Agent", "Opinion")
                .build();


        Response response = client.newCall(request).execute();
        if (response.code() == 200) {
            String body = response.body().string();
            JSONObject root = new JSONObject(body);
            JSONArray jsonGame = root.getJSONArray("results");

            for (int i = 0; i < jsonGame.length(); i++) {
                JSONObject game = jsonGame.getJSONObject(i);
                int id = game.getInt("id");
                createGameFromId(id, client);
            }
        }
    }

    public void createGameFromId(int id, OkHttpClient client) throws IOException {

        String url = "https://api.rawg.io/api/games/" + id + "?key=a63115890c184d4aaa49b2a5ec7fdbfc";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("User-Agent", "Opinion")
                .build();

        Response response = client.newCall(request).execute();

        if (response.code() == 200) {
            String body = response.body().string();
            JSONObject jsonGame = new JSONObject(body);


            String title = jsonGame.getString("name");
            String description = jsonGame.getString("description");
            String imageUrl = jsonGame.getString("background_image");
            JSONArray generes = jsonGame.getJSONArray("genres");

            Object release = jsonGame.get("released");
            String stringDate = release.toString();

            LocalDate releaseDate = stringDate.equals("null") ? null : LocalDate.parse(stringDate);


            if (title != null && description != null && !description.isBlank() && releaseDate != null && imageUrl != null && !gameService.existsByApiId(String.valueOf(id))) {

                Game game = new Game();

                game.setApiId(String.valueOf(id));
                game.setDescription(description);
                game.setTitle(title);
                game.setImageUrl(imageUrl);
                game.setReleaseDate(releaseDate);

                for (int i = 0; i < generes.length(); i++) {
                    JSONObject jsonGenre = generes.getJSONObject(i);
                    String genreName = jsonGenre.getString("name");
                    GameGenre gameGenre = gameGenreService.getByName(genreName);
                    game.getGenres().add(gameGenre);
                }
                gameService.save(game);
            }
        }
    }

}