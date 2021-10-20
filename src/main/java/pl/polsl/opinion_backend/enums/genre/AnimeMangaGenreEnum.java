package pl.polsl.opinion_backend.enums.genre;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AnimeMangaGenreEnum {
    ACTION("1", "Action"),
    ADVENTURE("2", "Adventure"),
    CARS("3", "Cars"),
    COMEDY("4", "Comedy"),
    DEMONS("6", "Demons"),
    MYSTERY("7", "Mystery"),
    DRAMA("8", "Drama"),
    FANTASY("10", "Fantasy"),
    GAME("11", "Game"),
    HISTORICAL("13", "Historical"),
    HORROR("14", "Horror"),
    MECHA("18", "Mecha"),
    MUSIC("19", "Music"),
    PARODY("20", "Parody"),
    SAMURAI("21", "Samurai"),
    ROMANCE("22", "Romance"),
    SCHOOL("23", "School");

    public final String apiId;
    public final String name;
}
