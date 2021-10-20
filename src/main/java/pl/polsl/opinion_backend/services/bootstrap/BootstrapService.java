package pl.polsl.opinion_backend.services.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.opinion_backend.entities.bootstrap.BootstrapStatus;
import pl.polsl.opinion_backend.entities.genre.MovieTvSeriesGenre;
import pl.polsl.opinion_backend.entities.role.RoleGroup;
import pl.polsl.opinion_backend.entities.user.User;
import pl.polsl.opinion_backend.enums.genre.AnimeMangaGenreEnum;
import pl.polsl.opinion_backend.enums.role.RoleGroupEnum;
import pl.polsl.opinion_backend.mappers.genre.GenreMapper;
import pl.polsl.opinion_backend.services.role.RoleGroupService;
import pl.polsl.opinion_backend.services.user.UserService;
import pl.polsl.opinion_backend.services.works.GenreService;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

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


    public void setup() {
        if (!bootstrapStatusService.isDone()) {
            log.info("Bootstrap already done");
        } else {
            try {
                // createRoleGroups();
                // createDefaultAdmin();
                // createDefaultUser();
                getGenreTypes();
                //animeGenerating();
                //booksGenerating();
                bootstrapStatusService.save(new BootstrapStatus(true));
            } catch (Exception e) {
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
//        generateMovieGeneres();
//        generateAnimeGeneres();
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

    }

    public void animeGenerating() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://jikan1.p.rapidapi.com/genre/anime/1/1")
                .get()
                .addHeader("x-rapidapi-host", "jikan1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();

        JSONObject root = new JSONObject(body);
    }

    public void booksGenerating() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://google-books.p.rapidapi.com/volumes?key=AIzaSyAOsteuaW5ifVvA_RkLXh0mYs6GLAD6ykc")
                .get()
                .addHeader("x-rapidapi-host", "google-books.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "72d9a3899cmsh897cee643145ec2p12af1bjsn96d67848f7a2")
                .build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();

        JSONObject root = new JSONObject(body);
    }

}