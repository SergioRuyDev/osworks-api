package com.sergioruy.osworksapi.exceptionHandler;

import com.sergioruy.osworksapi.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTitulo(ex.getMessage());
        exceptionResponse.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, exceptionResponse,  new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<ExceptionResponse.Campo>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new ExceptionResponse.Campo(nome, mensagem));
        }

        var exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(status.value());
        exceptionResponse.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto" +
                                            " e tente novamente");
        exceptionResponse.setDataHora(LocalDateTime.now());
        exceptionResponse.setCampos(campos);

        return super.handleExceptionInternal(ex, exceptionResponse, headers, status, request);
    }
}
