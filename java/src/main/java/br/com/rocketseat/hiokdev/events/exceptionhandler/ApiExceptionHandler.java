package br.com.rocketseat.hiokdev.events.exceptionhandler;

import br.com.rocketseat.hiokdev.events.dto.ProblemResponse;
import br.com.rocketseat.hiokdev.events.exceptions.BadRequestException;
import br.com.rocketseat.hiokdev.events.exceptions.ConflictException;
import br.com.rocketseat.hiokdev.events.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemResponse body = new ProblemResponse(status.value(), exception.getMessage());
        return handleExceptionInternal(exception, body,  new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handlerDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemResponse body = new ProblemResponse(status.value(), "Bad Request: Data Integrity Violation");
        return handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(BadRequestException exception, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemResponse body = new ProblemResponse(status.value(), exception.getMessage());
        return handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handlerConflictException(ConflictException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemResponse body = new ProblemResponse(status.value(), exception.getMessage());
        return handleExceptionInternal(exception, body, new HttpHeaders(), status, request);
    }

}
