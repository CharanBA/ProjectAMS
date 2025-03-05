package com.optimas.ams.exception;

import jakarta.ws.rs.core.Response;

public class DatabaseException extends CustomException {
    public DatabaseException(String message) {
        super(message, Response.Status.INTERNAL_SERVER_ERROR); // HTTP 500
    }
}
