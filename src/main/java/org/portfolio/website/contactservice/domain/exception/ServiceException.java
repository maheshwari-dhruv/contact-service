package org.portfolio.website.contactservice.domain.exception;

import org.portfolio.website.contactservice.domain.enums.HttpCodes;

import java.io.Serial;

public class ServiceException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 5720954041763348333L;

  private HttpCodes code;

  public ServiceException(HttpCodes code) {
    super(code.getMessage());
    this.code = code;
  }

  public ServiceException(String errorMessage, HttpCodes code) {
    super(errorMessage);
    this.code = code;
  }
}