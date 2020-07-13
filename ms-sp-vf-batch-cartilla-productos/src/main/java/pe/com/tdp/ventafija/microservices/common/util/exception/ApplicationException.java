package pe.com.tdp.ventafija.microservices.common.util.exception;

public class ApplicationException extends java.lang.Exception {
    private static final long serialVersionUID = 1L;

    public ApplicationException (java.lang.Exception e) {
        super(e);
    }

    public ApplicationException (String exceptionCode) {
        super(exceptionCode);
    }

    protected ApplicationException () {
        super();
    }
}
