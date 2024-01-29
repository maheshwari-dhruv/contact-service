package org.portfolio.website.contactservice.domain.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HttpCodes {
    SUCCESS(HttpStatus.OK.value(), "Ok", true),
    SYSTEM_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System exception"),
    UNKNOWN_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Unknown exception"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Internal Server Error"),
    PARAM_ILLEGAL(HttpStatus.BAD_REQUEST.value(),"Request param illegal"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),"Bad Request"),
    API_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Confluent API error"),
    DIFFERENT_MONTHS(HttpStatus.BAD_REQUEST.value(),"Date start and date end can't have different month"),
    INVALID_DATE(HttpStatus.NOT_FOUND.value(), "Invalid year or month"),
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
