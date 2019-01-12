package guru.springframework.spring5recipeapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRecipeIdException extends RuntimeException {

    public InvalidRecipeIdException() {
    }

    public InvalidRecipeIdException(String message) {
        super(message);
    }

    public InvalidRecipeIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRecipeIdException(Throwable cause) {
        super(cause);
    }

    public InvalidRecipeIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

