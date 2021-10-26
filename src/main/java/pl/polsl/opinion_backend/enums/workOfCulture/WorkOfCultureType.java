package pl.polsl.opinion_backend.enums.workOfCulture;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WorkOfCultureType {
    ANIME("Anime"),
    MANGA("Manga"),
    GAME("Game"),
    MOVIE("Movie"),
    TVSERIES("TvSeries");

    public final String name;
}
