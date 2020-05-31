package com.ecommerce.maha.service.exception;

import com.ecommerce.maha.error.ErrorEnumType;

public class ServiceException extends BaseApplicationException {
    public ServiceException(ErrorEnumType<? extends Enum<?>> errorEnumType) {

        super(errorEnumType);
    }

}

