package org.portfolio.website.contactservice.domain.exception;

import org.portfolio.website.contactservice.domain.enums.HttpCodes;

import java.io.Serial;

public class ParameterException extends ServiceException {

    @Serial
    private static final long serialVersionUID = -9205856733189831061L;

    private Object value;

    public ParameterException(String errorMessage, HttpCodes code) {
        super(errorMessage, code);
    }

    public ParameterException(Object value, String errorMessage, HttpCodes code) {
        super(errorMessage, code);
        this.value = value;
    }

}