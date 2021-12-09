package pl.polsl.opinion_backend.dtos.workOfCulture;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static pl.polsl.opinion_backend.exceptions.ErrorMessages.END_DATE_REQUIRED;
import static pl.polsl.opinion_backend.exceptions.ErrorMessages.START_DATE_REQUIRED;

@Data
@AllArgsConstructor
public class TimeDurationDTO {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = START_DATE_REQUIRED)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = END_DATE_REQUIRED)
    private LocalDate endDate;

}
