package tn.esprit.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DEFAULT_EXCEPTION_CODE(9999),
    GALAXY_NOT_FOUND(1001),
    GALAXY_NULL_NAME(1002),
    CALLING_PLANET_SERVER(1003);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

}
