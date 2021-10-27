package pl.polsl.opinion_backend.enums.genre;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenreType {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    MYSTERY("Myster"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    PARODY("Parody"),
    ROMANCE("Romance"),
    INDIE("Indie"),
    CASUAL("Casual"),
    SPORT("Sport"),
    FIGHTING("Fighting"),
    FAMILY("Family"),
    EDUCATIONAL("Educational"),
    CRIME("Crime"),
    ANIMATION("Animation"),
    THRILLER("Thriller"),
    WESTERN("Western"),
    BIOGRAPHY("Biography"),
    WAR("War"),
    MUSICAL("Musical"),
    DOCUMENTARY("Documentary");

    private final String name;
}
