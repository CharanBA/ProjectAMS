
package com.optimas.ams.repository;

import com.optimas.ams.model.ComponentDef;
import com.optimas.ams.utility.OrientDBUtil;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.record.impl.ODocument;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComponentRepository {

	public boolean save(ComponentDef component) {
		System.out.println("Saving component to OrientDB...");

		try (ODatabaseSession db = OrientDBUtil.getSession()) {
			if (db.getClass("ComponentDef") == null) {
				db.createVertexClass("ComponentDef");
			}

			OVertex componentVertex = db.newVertex("ComponentDef");
			componentVertex.setProperty("id", component.getId());
			componentVertex.setProperty("name", component.getName());
			componentVertex.setProperty("category", component.getCategory());
			componentVertex.setProperty("manufacturer", component.getManufacturer());
			componentVertex.setProperty("serialNumber", component.getSerialNumber());
			componentVertex.setProperty("warrantyEnd", component.getWarrantyEnd());

			componentVertex.save();
			System.out.println("Component saved: " + component.getName());
			return true;
		} catch (Exception e) {
			System.err.println("Error saving component: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	 public List<ComponentDef> getAllComponents() {
	        List<ComponentDef> components = new ArrayList<>();
	        String query = "SELECT * FROM ComponentDef";

	        try (ODatabaseSession db = OrientDBUtil.getSession(); var resultSet = db.query(query)) {
	            while (resultSet.hasNext()) {
	                var element = resultSet.next().getElement().orElse(null);
	                if (element instanceof ODocument componentDoc) {
	                    ComponentDef component = new ComponentDef();
	                    component.setId(componentDoc.getProperty("id"));
	                    component.setName(componentDoc.getProperty("name"));
	                    component.setCategory(componentDoc.getProperty("category"));
	                    component.setManufacturer(componentDoc.getProperty("manufacturer"));
	                    component.setSerialNumber(componentDoc.getProperty("serialNumber"));
	                    component.setWarrantyEnd(componentDoc.getProperty("warrantyEnd"));
	                    components.add(component);
	                }
	            }
	        } catch (Exception e) {
	            System.err.println("Error fetching components: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return components;
	    }
	
	public List<ComponentDef> getComponentsByAssetId(String assetId) {
		System.out.println("Fetching components for asset ID: " + assetId);

		List<ComponentDef> components = new ArrayList<>();
		String query = "SELECT expand(out('HAS_COMPONENT')) FROM AssetDef WHERE id = ?";

		try (ODatabaseSession db = OrientDBUtil.getSession(); var resultSet = db.query(query, assetId)) {
			while (resultSet.hasNext()) {
				var element = resultSet.next().getElement().orElse(null);
				if (element instanceof com.orientechnologies.orient.core.record.impl.ODocument componentDoc) {
					ComponentDef component = new ComponentDef();
					component.setId(componentDoc.getProperty("id"));
					component.setName(componentDoc.getProperty("name"));
					component.setCategory(componentDoc.getProperty("category"));
					component.setManufacturer(componentDoc.getProperty("manufacturer"));
					component.setSerialNumber(componentDoc.getProperty("serialNumber"));
					component.setWarrantyEnd(componentDoc.getProperty("warrantyEnd"));
					components.add(component);
				}
			}
		} catch (Exception e) {
			System.err.println("Error fetching components: " + e.getMessage());
			e.printStackTrace();
		}
		return components;
	}
}
