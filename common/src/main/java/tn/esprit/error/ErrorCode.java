package tn.esprit.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DEFAULT_EXCEPTION_CODE(9999),
    PLANET_NOT_FOUND(1001),
    CALLING_GALAXY_SERVER(1002),
    GALAXY_NOT_FOUND(1003),
    GALAXY_NULL_NAME(1004),
    CALLING_PLANET_SERVER(1005),
    GALAXY_UNAUTHORIZED(1006);
    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

}
