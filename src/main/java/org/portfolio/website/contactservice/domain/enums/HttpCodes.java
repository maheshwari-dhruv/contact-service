package org.portfolio.website.contactservice.domain.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HttpCodes {
    SUCCESS(HttpStatus.OK.value(), "Ok", true),
    SYSTEM_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System exception"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),"Bad Request"),
    ;

    private final int httpCode;
    private final String message;
    private boolean success;

    HttpCodes(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    HttpCodes(int httpCode, String message, boolean success) {
        this.httpCode = httpCode;
        this.message = message;
        this.success = success;
    }
}
