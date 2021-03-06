package pl.kuba.school.exception;

public class ControllerException extends RuntimeException {

    private ControllerError controllerError;

    public ControllerException(ControllerError controllerError) {
        this.controllerError = controllerError;
    }

    public ControllerError getStudentError() {
        return controllerError;
    }

}
