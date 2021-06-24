package com.organize.school.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageError {
    private MessageSource messageSource;


    public MessageError(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    public ApiError create(String code){
        return new ApiError(code,
                messageSource.getMessage(code, null, LocaleContextHolder.getLocale()));
    }

    public ApiError create(String code, List<String> args){
        return new ApiError(code,
                messageSource.getMessage(code, args.toArray(), LocaleContextHolder.getLocale()));
    }

    public ApiError create(String code, String description){
        return new ApiError(code, description);
    }

    @Data
    @EqualsAndHashCode
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ApiError{

        private String code;
        private String description;

        private ApiError(){

        }

        public ApiError(String code, String description){
            this.code = code;
            this.description = description;
        }

    }

}
