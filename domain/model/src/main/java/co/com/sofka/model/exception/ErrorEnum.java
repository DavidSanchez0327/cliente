package co.com.sofka.model.exception;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    DONT_EXIST_PERSON("No existe la persona"),
    ERROR_VALIDATE_CUSTOMER("No existe el cliente"),
    ERROR_CREATE_PERSON("Fallo la creacion de la persona");

    private final String message;

    ErrorEnum(String message) {
        this.message = message;
    }
}
