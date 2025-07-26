package org.skypro.skyshop.exceptions;

public final class StoreError {
    private final String code;
    private final String message;

    public StoreError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Продукт не найден";
    }
}
