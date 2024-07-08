package tn.esprit.error.exception;

public class ClientSideCustomException extends CustomException {

    public ClientSideCustomException(String message, int errorCode) {
        super(message, errorCode);
    }
}
