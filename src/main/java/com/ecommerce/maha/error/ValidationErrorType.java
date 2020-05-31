package com.ecommerce.maha.error;

public enum ValidationErrorType implements ErrorEnumType<ValidationErrorType> {
    INVALID_DATA(1, "Invalid data."),
    UNABLE_TO_FIND_CART(2, "Unable to find cart.");

    private int code;
    private String errorMessage;

    private ValidationErrorType(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return this.code;
    }

    public String getAppCode() {
        return "CART-" + String.format("%04d", this.code);
    }

    public String getAppMessage() {
        return this.errorMessage;
    }
}
