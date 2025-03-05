package com.optimas.ams.controller;

import com.optimas.ams.model.AssetDef;
import com.optimas.ams.service.AssetService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Path("/asset")  
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAsset(AssetDef asset) {
        assetService.saveAsset(asset);
        return Response.status(Response.Status.CREATED)
                .entity("Asset created successfully!")
                .build();
    }
    
    @GET
//  @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssetById(@QueryParam("id") String id) {
        AssetDef asset = assetService.getAssetById(id);
        return Response.ok(asset).build();
    }
    
    @GET
    @Path("/all")  
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAssets() {
        List<AssetDef> assets = assetService.getAllAssets();
        if (!assets.isEmpty()) {
            return Response.ok(assets).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No assets found").build();
        }
    }
  
    @POST
  @Path("/{id}/component/{componentId}") 
//    @Path("/component")
    @Produces(MediaType.APPLICATION_JSON)
    public Response linkComponentToAsset(@PathParam("id") String assetId, @PathParam("componentId") String componentId) {
        boolean success = assetService.linkComponentToAsset(assetId, componentId);
        if (success) {
            return Response.ok("Component linked successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to link component").build();
        }
    }
}
