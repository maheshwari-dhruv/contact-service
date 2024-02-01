package org.portfolio.website.contactservice.domain.dto.base;

import lombok.Data;
import lombok.ToString;
import org.portfolio.website.contactservice.domain.enums.HttpCodes;

import java.io.Serializable;

@Data
@ToString
public class GenericResponse<T> implements Serializable {

    private boolean success;
    private int resultCode;
    private String resultStatus;
    private String resultMsg;

    private T data;

    public GenericResponse() {}

    public GenericResponse(String errorMsg, HttpCodes codes) {
        this.setHttpCode(codes);
        this.resultMsg = errorMsg;
    }

    public GenericResponse(HttpCodes e) {
        this.setHttpCode(e);
    }

    public GenericResponse(HttpCodes e, T data) {
        this.setHttpCode(e, data);
    }

    public void setHttpCode(HttpCodes code) {
        this.success = code.isSuccess();
        this.resultCode = code.getHttpCode();
        this.resultStatus = code.name();
        this.resultMsg = code.getMessage();
    }

    public void setHttpCode(HttpCodes code, T data) {
        this.success = code.isSuccess();
        this.resultCode = code.getHttpCode();
        this.resultStatus = code.name();
        this.resultMsg = code.getMessage();
        this.data = data;
    }

    public static <T> GenericResponse success(T data) {
        return new GenericResponse(HttpCodes.SUCCESS, data);
    }

    public static <T> GenericResponse error() {
        return new GenericResponse(HttpCodes.INTERNAL_SERVER_ERROR, null);
    }

    public static <T> GenericResponse success() {
        return new GenericResponse(HttpCodes.SUCCESS, null);
    }
}