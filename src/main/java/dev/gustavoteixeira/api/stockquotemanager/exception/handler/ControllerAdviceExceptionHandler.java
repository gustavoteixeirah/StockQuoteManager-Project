package dev.gustavoteixeira.api.stockquotemanager.exception.handler;

import dev.gustavoteixeira.api.stockquotemanager.exception.StockNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {StockNotRegisteredException.class})
    public ResponseEntity<String> handleAgendaAlreadyExistsException(StockNotRegisteredException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body("The informed Stock is not registered.");
    }

}
