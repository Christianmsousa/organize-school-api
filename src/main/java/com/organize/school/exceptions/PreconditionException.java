package com.organize.school.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import com.organize.school.exceptions.MessageError.ApiError;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class PreconditionException extends RuntimeException{

    private final List<ApiError> errors;

    public PreconditionException(ApiError error){
        this(List.of(error));
    }

    public PreconditionException(List<ApiError> errors){
        super(errors.toString());
        this.errors = errors;
    }
}
