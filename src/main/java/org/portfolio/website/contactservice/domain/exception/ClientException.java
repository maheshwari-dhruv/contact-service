package org.portfolio.website.contactservice.domain.exception;

import org.portfolio.website.contactservice.domain.enums.HttpCodes;

import java.io.Serial;

public class ClientException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5720954041763348333L;

    private String clientName;
    private String errorMessage;
    private HttpCodes errorCode;

    public ClientException(String clientName, String errorMessage) {
        super(errorMessage);
        this.clientName = clientName;
    }

    public ClientException(String clientName, HttpCodes errorCode, String errorMessage) {
        super(errorMessage);
        this.clientName = clientName;
        this.errorCode = errorCode;
    }
}