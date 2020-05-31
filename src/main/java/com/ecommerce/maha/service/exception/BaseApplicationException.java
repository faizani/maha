package com.ecommerce.maha.service.exception;

import com.ecommerce.maha.error.ErrorEnumType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class BaseApplicationException extends RuntimeException {

    private HttpStatus httpStatus;

    private String appCode;

    private String appMessage;

    private boolean logStackTrace;

    private boolean publishable;

    private Map<String, String> fields;

    private Map<String, String> publishableFields;

    protected BaseApplicationException(ErrorEnumType<? extends Enum<?>> errorEnumType) {

        super(errorEnumType.getExceptionMessage());
        this.setAppCode(errorEnumType.getAppCode());
        this.setAppMessage(errorEnumType.getAppMessage());
        this.setHttpStatus(errorEnumType.getHttpStatus());
        this.fields = new HashMap();
        this.logStackTrace = true;
        this.publishable = true;
        this.publishableFields = new HashMap();
    }
}
