package pl.polsl.opinion_backend.mappers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.polsl.opinion_backend.mappers.qualifires.AgeMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class AgeMapper {

    @AgeMapping
    public int ageFromLocalDateMapping(LocalDate date) {
        LocalDate now = LocalDate.now();
        return (int) ChronoUnit.YEARS.between(date, now);
    }

}
