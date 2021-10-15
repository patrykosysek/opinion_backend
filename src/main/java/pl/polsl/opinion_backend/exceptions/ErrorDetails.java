package pl.polsl.opinion_backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class ErrorDetails {
    private String code;

    public static List<ErrorDetails> errorDetailsList(String message) {
        return Collections.singletonList(new ErrorDetails(message));
    }

    public static List<ErrorDetails> errorDetailsList(Collection<String> messages) {
        return messages.stream().map(ErrorDetails::new).collect(Collectors.toList());
    }
}
