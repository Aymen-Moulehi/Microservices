package tn.esprit.error.exception;

public class ServerSideCustomException extends CustomException{
    public ServerSideCustomException(String message, int errorCode) {
        super(message, errorCode);
    }
}
