package org.portfolio.website.contactservice.domain.exception;

import org.portfolio.website.contactservice.domain.enums.HttpCodes;

import java.io.Serial;
import java.text.MessageFormat;

public class ValidatedException extends ServiceException {

    @Serial
    private static final long serialVersionUID = -9205856733189831061L;

    private Object value;

    public ValidatedException(HttpCodes code) {
        super(code.getMessage(), code);
    }

    public ValidatedException(String errorMessage, HttpCodes code) {
        super(errorMessage, code);
    }

    public ValidatedException(Object value, String errorMessage, HttpCodes code) {
        super(MessageFormat.format(errorMessage, value), code);
        this.value = value;
    }

}