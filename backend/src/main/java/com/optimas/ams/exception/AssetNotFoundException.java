package com.optimas.ams.exception;

import jakarta.ws.rs.core.Response;

public class AssetNotFoundException extends CustomException {
    public AssetNotFoundException(String s) {
        super(s, Response.Status.NOT_FOUND);
    }
}
