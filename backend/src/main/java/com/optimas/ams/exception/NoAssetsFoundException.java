package com.optimas.ams.exception;

import jakarta.ws.rs.core.Response;

public class NoAssetsFoundException extends CustomException {
	 public NoAssetsFoundException(String assetId) {
	        super("Asset with ID " + assetId + " not found", Response.Status.NOT_FOUND);
	    }
}
