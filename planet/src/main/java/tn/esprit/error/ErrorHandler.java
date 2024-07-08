package tn.esprit.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.error.exception.ClientSideCustomException;
import tn.esprit.error.exception.CustomException;

public class ErrorHandler {
    public static ResponseEntity<ErrorEntity> handleException(Exception exception) {
        ErrorEntity errorEntity;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof CustomException customException) {
            errorEntity = new ErrorEntity(
                    customException.getErrorCode(),
                    customException.getMessage()
            );
            if (exception instanceof ClientSideCustomException) {
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } else {
            errorEntity = new ErrorEntity(
                    ErrorCode.DEFAULT_EXCEPTION_CODE.getCode(),
                    exception.getMessage()
            );
        }
        return new ResponseEntity<>(errorEntity, httpStatus);
    }
}
