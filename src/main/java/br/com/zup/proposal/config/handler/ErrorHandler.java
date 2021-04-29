package br.com.zup.proposal.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus ( HttpStatus.BAD_REQUEST )
    @ExceptionHandler ( MethodArgumentNotValidException.class )
    public List<ErrorHandlerResponse> handler ( MethodArgumentNotValidException exception ) {
        List<ErrorHandlerResponse> responses = new ArrayList<>();
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();

        fieldError.forEach(e -> {
            ErrorHandlerResponse error = new ErrorHandlerResponse(e.getField() , e.getDefaultMessage());
            responses.add(error);
        });
        return responses;
    }
}
