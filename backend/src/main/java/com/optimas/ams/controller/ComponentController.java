package com.optimas.ams.controller;

import com.optimas.ams.model.ComponentDef;
import com.optimas.ams.repository.ComponentRepository;
import com.optimas.ams.service.ComponentService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Path("/component")
public class ComponentController {

    private final ComponentRepository componentRepository;

    public ComponentController(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComponent(ComponentDef component) {
        boolean success = ComponentService.save(component);
        if (success) {
            return Response.status(Response.Status.CREATED).entity("Component added successfully").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add component").build();
        }
    }
    
    @GET
    @Path("/all") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComponents() {
    	
        List<ComponentDef> components = ComponentService.getAllComponents();
        
        if (!components.isEmpty()) {
            return Response.ok(components).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No components found").build();
        }
    }

    @GET
  @Path("/asset/{id}/components")
//    @Path("/components")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComponentsByAssetId(@PathParam("id") String assetId) {
    	
        List<ComponentDef> components = ComponentService.getComponentsByAssetId(assetId);
        if (!components.isEmpty()) {
            return Response.ok(components).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No components found for asset ID: " + assetId).build();
        }
    }
}
