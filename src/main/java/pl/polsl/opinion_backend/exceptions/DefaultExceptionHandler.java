package pl.polsl.opinion_backend.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.NoSuchElementException;


@ControllerAdvice
@RestController
@RequiredArgsConstructor
@Slf4j
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDetails(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDetails(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDetails(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(e.getMessage());
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        status = checkForbidden(message, status);
        return new ResponseEntity<>(
                new ErrorDetails(message),
                status
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        log.error(e.toString());
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), checkForbidden(e.getMessage(), HttpStatus.NOT_FOUND), request);
    }

    @ExceptionHandler(AccountStatusException.class)
    protected ResponseEntity<Object> handleAccountStatusException(AccountStatusException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDetails(e.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    private HttpStatus checkForbidden(String message, HttpStatus status) {
        return message != null && message.contains("FORBIDDEN") ? HttpStatus.FORBIDDEN : status;
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Object> handleJWTVerificationException(JWTVerificationException e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(
                new ErrorDetails(e.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }
    
}
