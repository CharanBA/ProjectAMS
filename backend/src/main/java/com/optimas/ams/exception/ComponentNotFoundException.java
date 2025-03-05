package com.optimas.ams.exception;

import jakarta.ws.rs.core.Response;

public class ComponentNotFoundException extends CustomException {
    public ComponentNotFoundException(String assetId) {
        super("No components found for asset ID: " + assetId, Response.Status.NOT_FOUND);
    }
}
