package org.portfolio.website.contactservice.domain.exception;

import org.portfolio.website.contactservice.domain.enums.HttpCodes;

import java.io.Serial;

public class MailException extends ServiceException {

    @Serial
    private static final long serialVersionUID = -9205856733189831061L;

    private Object value;

    public MailException(String errorMessage, HttpCodes code) {
        super(errorMessage, code);
    }

    public MailException(Object value, String errorMessage, HttpCodes code) {
        super(errorMessage, code);
        this.value = value;
    }
}
