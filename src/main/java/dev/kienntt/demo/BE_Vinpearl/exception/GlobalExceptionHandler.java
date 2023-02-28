package dev.kienntt.demo.BE_Vinpearl.exception;

import dev.kienntt.demo.BE_Vinpearl.base.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseMessage handleAllException(Exception ex, WebRequest request) {
        if (ex.getLocalizedMessage().contains("JWT expired")) return new ResponseMessage(404, "Error", null, "Jwt token is expired");

        return new ResponseMessage(404, "Error", null, ex.getLocalizedMessage());
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseMessage TodoException(Exception ex,  WebRequest request) {
        return new ResponseMessage(400, "Unknow error", null, ex.getLocalizedMessage());
    }
}
