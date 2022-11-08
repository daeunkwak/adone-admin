package app.adoneadmin.global.exception.handler;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
