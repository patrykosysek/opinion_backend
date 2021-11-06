package pl.polsl.opinion_backend.enums.genre;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
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
    DOCUMENTARY("Documentary"),
    CARS("Cars"),
    DEMONS("Demons"),
    GAME("Game"),
    MECHA("Mecha"),
    SAMURAI("Samurai"),
    SCHOOL("School"),
    RPG("RPG"),
    STRATEGY("Strategy"),
    SHOOTER("Shooter"),
    SIMULATION("Simulation"),
    PUZZLE("Puzzle"),
    ARCADE("Arcade"),
    PLATFORMER("Platformer"),
    RACING("Racing"),
    CARD("Card"),
    TALK_SHOW("Talk-Show"),
    SHORT("Short"),
    SCI_FI("Sci-Fi"),
    REALITY_TV("Reality-Tv"),
    NEWS("News"),
    MUSIC("Music"),
    HISTORY("History"),
    GAME_SHOW("Game-Show"),
    ADULT("Adult"),
    MASSIVELY_MULTIPLAYER("Massively Multiplayer"),
    BOARD_GAMES("Board Games");


    private final String name;
}
