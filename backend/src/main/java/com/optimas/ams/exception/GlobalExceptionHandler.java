package com.optimas.ams.exception;

import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<CustomException> {
    @Override
    public Response toResponse(CustomException exception) {
        return exception.getResponse();
    }
}
