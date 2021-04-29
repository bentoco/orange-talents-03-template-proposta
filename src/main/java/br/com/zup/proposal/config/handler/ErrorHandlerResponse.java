package br.com.zup.proposal.config.handler;

public class ErrorHandlerResponse {
    private String field;
    private String message;

    public ErrorHandlerResponse ( String field , String message ) {
        this.field = field;
        this.message = message;
    }

    public String getField () {
        return field;
    }

    public String getMessage () {
        return message;
    }
}
