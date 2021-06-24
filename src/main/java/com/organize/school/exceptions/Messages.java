package com.organize.school.exceptions;

public interface Messages {

    // 400
    String FIELD_VALIDATION = "400.001";
    String JSON_VALIDATION = "400.002";
    String REQUIRED_REQUEST_BODY = "400.003";
    String USERNAME_NOT_FOUND = "400.004";
    String INSTITUTION_NOT_FOUND = "400.005";

    //403
    String USER_OR_PASSWORD_INVALID = "403.001";

    //404
    String PERMISSION_NOT_FOUND = "404.001";
    String ADDRESS_NOT_FOUND = "404.002";

    //403
    String NOT_PERMISSION = "403.002";

    //405
    String OPERATION_NOT_ALLOWED_THIS_USER = "405.001";

    //409
    String CARD_DUPLICITY = "409.001";

    //412
    String INVALID_EMAIL = "412.001";
    String USER_ALREADY_REGISTERED = "412.002";
    String INSTITUTION_ALREADY_REGISTERED = "412.003";
    String COURSE_ALREADY_REGISTERED = "412.004";
    String SUBJECT_ALREADY_REGISTERED = "412.005";
    String COURSE_NOT_EXIST = "412.006";
    // 422
    String CONTACT_SYSTEM_ADMIN = "422.001";
    String USER_ASSOCIATED_MERCHANT = "422.002";


    //500
    String ERROR_ON_CALL_LOGIN = "500.003";
    String ERROR_POST_USED_SERVICE = "500.005";
}
