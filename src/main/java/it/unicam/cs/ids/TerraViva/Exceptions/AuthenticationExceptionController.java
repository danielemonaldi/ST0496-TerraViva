package it.unicam.cs.ids.TerraViva.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionController {
    @ExceptionHandler(value = ExistingUserException.class)
    public ResponseEntity<Object> exception(ExistingUserException exception) {
        return new ResponseEntity<>("A user with this username already exists", HttpStatus.CONFLICT);
    }
}