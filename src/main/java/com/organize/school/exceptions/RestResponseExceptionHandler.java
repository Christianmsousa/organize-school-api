package com.organize.school.exceptions;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.organize.school.exceptions.MessageError.ApiError;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;


@RestControllerAdvice
public class RestResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    public static final String DETALHE = "%s - Detalhes: %s";
    private MessageError messageError;

    public RestResponseExceptionHandler(MessageError messageError){
        this.messageError = messageError;
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError> errors = newArrayList();

        if (Objects.isNull(ex.getCause())) {
            errors.add(messageError.create(Messages.REQUIRED_REQUEST_BODY));
        } else if (ex.getRootCause() instanceof MismatchedInputException) {
            MismatchedInputException mie = (MismatchedInputException) ex.getRootCause();
            if (mie.getTargetType() != null && mie.getTargetType().getEnumConstants() != null) {
                errors.add(messageError.create(Messages.FIELD_VALIDATION,
                        format("Invalid type for field '%s' - Available values '%s'.",
                                mie.getPath().stream().map(
                                                p -> Objects.nonNull(p.getFieldName()) ? p.getFieldName() : "[" + p.getIndex() + "]")
                                        .collect(joining(".")),
                                Arrays.toString(mie.getTargetType().getEnumConstants()))));
            } else if(Objects.nonNull(mie.getTargetType())) {
                String type;
                if (mie.getTargetType().getSuperclass().equals(Object.class)) {
                    type = mie.getTargetType().getSimpleName();
                } else {
                    type = mie.getTargetType().getSuperclass().getSimpleName();
                }
                errors.add(messageError.create(Messages.FIELD_VALIDATION,
                        format("Invalid type for field '%s' - Type '%s' expected.", mie.getPath().stream().map(
                                        p -> Objects.nonNull(p.getFieldName()) ? p.getFieldName() : "[" + p.getIndex() + "]")
                                .collect(joining(".")), type)));
            } else {
                errors.add(messageError.create(Messages.CONTACT_SYSTEM_ADMIN));
            }
        } else if (ex.getRootCause() instanceof JsonParseException) {
            JsonParseException jpe = (JsonParseException) ex.getRootCause();
            errors.add(messageError.create(Messages.JSON_VALIDATION,
                    format("Invalid JSON: %s", jpe.getMessage())));
        } else {
            errors.add(messageError.create(Messages.CONTACT_SYSTEM_ADMIN));
        }

        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<List<ApiError>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(ex.getError()));
    }

    @ExceptionHandler(value = PreconditionException.class)
    protected ResponseEntity<List<ApiError>> handlePredcondition(PreconditionException ex) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getErrors());
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<List<ApiError>> handleUserExpcetion(UserException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of(ex.getError()));
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiError> handlerPasswordException(){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageError.create(Messages.USER_OR_PASSWORD_INVALID));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError> errors = newArrayList();
        ex.getBindingResult().getFieldErrors().forEach(fe -> {
            errors.add(messageError.create(Messages.FIELD_VALIDATION,
                    format("Field '%s' %s", fe.getField(), fe.getDefaultMessage())));
        });

        if (errors.isEmpty()) {
            errors.add(messageError.create(Messages.CONTACT_SYSTEM_ADMIN));
        }


        return ResponseEntity.status(status).body(errors);
    }
}
