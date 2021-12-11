package pl.kuba.school.exception;

public enum ControllerError {

    NOT_FOUND("Resource does not exist"),
    EMAIL_ALREADY_EXISTS("Email already exists");

    private String message;

    ControllerError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
