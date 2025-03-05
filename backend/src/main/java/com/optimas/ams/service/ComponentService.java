package com.optimas.ams.service;

import com.optimas.ams.exception.ComponentNotFoundException;
import com.optimas.ams.model.ComponentDef;
import com.optimas.ams.repository.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ComponentService {

	private static ComponentRepository componentRepository;

	public ComponentService(ComponentRepository componentRepository) {
		this.componentRepository = componentRepository;
	}

	public static boolean save(ComponentDef component) {
		component.setId(UUID.randomUUID().toString());
		return componentRepository.save(component);
	}
	
	 public static List<ComponentDef> getAllComponents() {
	        List<ComponentDef> components = componentRepository.getAllComponents();
	        if (components.isEmpty()) {
	            throw new ComponentNotFoundException("No components found in the system.");
	        }
	        return components;
	    }
	
	public static List<ComponentDef> getComponentsByAssetId(String assetId) {
		 List<ComponentDef> components = componentRepository.getComponentsByAssetId(assetId);
	        if (components.isEmpty()) {
	            throw new ComponentNotFoundException(assetId);
	        }
	        return components;
	}
}
