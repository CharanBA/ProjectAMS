package com.optimas.ams.exception;

import jakarta.ws.rs.core.Response;

public class AssetAlreadyExistsException extends CustomException {
    public AssetAlreadyExistsException(String message) {
        super(message, Response.Status.CONFLICT); // HTTP 409 Conflict
    }
}
