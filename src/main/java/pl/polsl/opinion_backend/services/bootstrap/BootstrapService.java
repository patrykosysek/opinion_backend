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
import pl.polsl.opinion_backend.entities.genre.AnimeMangaGenre;
import pl.polsl.opinion_backend.entities.genre.GameGenre;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.entities.worksOfCulture.Anime;
import pl.polsl.opinion_backend.entities.worksOfCulture.Manga;
import pl.polsl.opinion_backend.entities.worksOfCulture.Movie;
import pl.polsl.opinion_backend.entities.worksOfCulture.TvSeries;
import pl.polsl.opinion_backend.enums.genre.AnimeMangaGenreEnum;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;
import pl.polsl.opinion_backend.helpers.EncodingUrl;
import pl.polsl.opinion_backend.mappers.genre.GenreMapper;
import pl.polsl.opinion_backend.services.role.RoleGroupService;
import pl.polsl.opinion_backend.services.user.UserService;
import pl.polsl.opinion_backend.services.works.AnimeMangaGenreService;
import pl.polsl.opinion_backend.services.works.GenreService;
import pl.polsl.opinion_backend.services.works.MovieTvSeriesGenreService;
import pl.polsl.opinion_backend.services.works.WorkOfCultureService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BootstrapService {
    private final PasswordEncoder passwordEncoder;
    private final RoleGroupService roleGroupService;
    private final UserService userService;
    private final BootstrapStatusService bootstrapStatusService;
    private final GenreService genreService;
    private final GenreMapper genreMapper;
    private final AnimeMangaGenreService animeMangaGenreService;
    private final WorkOfCultureService workOfCultureService;
    private final MovieTvSeriesGenreService movieTvSeriesGenreService;

    public void setup() {
        if (!bootstrapStatusService.isDone()) {
            log.info("Bootstrap already done");
        } else {
            try {
//                createRoleGroups();
//                createDefaultAdmin();
//                createDefaultUser();
//                getGenreTypes();
//                animeGenerating();
//                mangaGenerating();
//                movieGenerating();
                tvSeriesGenerating();
//                bootstrapStatusService.save(new BootstrapStatus(true));
            } catch (Exception e) {
                log.info(e.getMessage());
                log.info("Bootstrap failed");
            }

        }
    }

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
                new HashSet<>(),
                true
        );
        user.getRoleGroups().add(roleGroupService.getByRoleName("ADMIN"));
        userService.save(user);
    }

    public void createDefaultUser() {
        User user = new User(
                "opinion_user@onet.pl",
                passwordEncoder.encode("OPINION_USER"),
                new HashSet<>(),
                true
        );
        user.getRoleGroups().add(roleGroupService.getByRoleName("OPINION_USER"));
        userService.save(user);
    }

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

    public void animeGenerating() {
        OkHttpClient client = new OkHttpClient();

        List<AnimeMangaGenre> genres = ImmutableList.copyOf(animeMangaGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createAnimeFromGenre(genre, client);
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

                if (title != null && description != null && releaseDate != null && imageUrl != null) {
                    anime.setTitle(title);
                    anime.setApiId(apiId);
                    anime.setDescription(description);
                    anime.setReleaseDate(releaseDate);
                    anime.setImageUrl(imageUrl);
                    anime.getGenres().add(genre);

                    workOfCultureService.save(anime);
                }
            }
        }
        log.info("Anime generated");
    }

    public Anime getAnimeWithMoreInformation(String title, OkHttpClient client) throws IOException {
        String url = "https://jikan1.p.rapidapi.com/anime/" + EncodingUrl.getUrlPathFromString(title) + "/moreinfo";


        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();


        Response response = client.newCall(request).execute();
        String body = response.body().string();

        return null;
    }

    public void mangaGenerating() {
        OkHttpClient client = new OkHttpClient();

        List<AnimeMangaGenre> genres = ImmutableList.copyOf(animeMangaGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createMangaFromGenre(genre, client);
            } catch (IOException e) {
                log.info(e.getMessage());
                log.info("Manga creation failed");
            }
        });

    }

    public void createMangaFromGenre(AnimeMangaGenre genre, OkHttpClient client) throws IOException {


        for (int page = 1; page < 10; page++) {
            String url = "https://jikan1.p.rapidapi.com/search/manga?page=" + page + "&genre=" + genre.getApiId();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                    .build();

            Response response = client.newCall(request).execute();
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

                if (title != null && description != null && releaseDate != null && imageUrl != null) {
                    manga.setTitle(title);
                    manga.setApiId(apiId);
                    manga.setDescription(description);
                    manga.setReleaseDate(releaseDate);
                    manga.setImageUrl(imageUrl);
                    manga.getGenres().add(genre);

                    workOfCultureService.save(manga);
                }
            }
        }
        log.info("Anime generated");
    }

    public void movieGenerating() {

        OkHttpClient client = new OkHttpClient();

        List<MovieTvSeriesGenre> genres = ImmutableList.copyOf(movieTvSeriesGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createMovieFromGenre(genre, client);
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
        String body = response.body().string();

        JSONObject root = new JSONObject(body);
        JSONArray result = root.getJSONArray("results");

        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonAnime = result.getJSONObject(i);

            String imdbId = jsonAnime.getString("imdb_id");
            createMovieFromImdbId(imdbId, client);
        }

        log.info("Movie generated");
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

        if (title != null && description != null && releaseDate != null && imageUrl != null) {
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
            workOfCultureService.save(movie);
        }

    }


    public void tvSeriesGenerating() {
        OkHttpClient client = new OkHttpClient();

        List<MovieTvSeriesGenre> genres = ImmutableList.copyOf(movieTvSeriesGenreService.findAll());

        genres.forEach(genre -> {
            try {
                createTvSeriesFromGenre(genre, client);
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
        String body = response.body().string();

        JSONObject root = new JSONObject(body);
        JSONArray result = root.getJSONArray("results");

        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonTvSeries = result.getJSONObject(i);

            String imdbId = jsonTvSeries.getString("imdb_id");
            createTvSeriesFromImdbId(imdbId, client);
        }

        log.info("Tv series generated");
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

        if (title != null && description != null && releaseDate != null && imageUrl != null) {
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
            workOfCultureService.save(tvSeries);
        }

    }

}
