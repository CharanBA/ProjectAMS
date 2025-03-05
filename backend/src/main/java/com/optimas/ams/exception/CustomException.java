package com.optimas.ams.exception;

import com.optimas.ams.utility.ErrorResponse;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class CustomException extends WebApplicationException {
    public CustomException(String message, Response.Status status) {
        super(Response.status(status)
                .entity(new ErrorResponse(message, status.getStatusCode()))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
