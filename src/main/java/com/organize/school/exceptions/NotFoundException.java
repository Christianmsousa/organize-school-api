package com.organize.school.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.organize.school.exceptions.MessageError.ApiError;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class NotFoundException extends RuntimeException {

    private final ApiError error;

    public NotFoundException(ApiError error){
        super(error.toString());
        this.error = error;
    }

    public NotFoundException(ApiError error, String detalhes){
        super(String.format("%s - Detalhes: %s", error.toString(), detalhes));
        this.error = error;
    }
}
