package com.optimas.ams.repository;

import com.optimas.ams.model.AssetDef;
import com.optimas.ams.utility.OrientDBUtil;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.record.OEdge;
import com.orientechnologies.orient.core.record.OElement;
import com.orientechnologies.orient.core.record.ORecord;
import com.orientechnologies.orient.core.record.OVertex;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.executor.OResultSet;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class AssetRepository {

    public void save(AssetDef asset) {
        System.out.println("Saving asset to OrientDB...");

        try (ODatabaseSession db = OrientDBUtil.getSession()) { 
            
            if (db.getClass("AssetDef") == null) {
                db.createVertexClass("AssetDef");
            }

            OVertex assetVertex = db.newVertex("AssetDef");
            assetVertex.setProperty("id", asset.getId());
            assetVertex.setProperty("name", asset.getName());
            assetVertex.setProperty("category", asset.getCategory());
            assetVertex.setProperty("description", asset.getDescription());
            assetVertex.setProperty("purchaseDate", asset.getPurchaseDate());
            assetVertex.setProperty("status", asset.getStatus());
            assetVertex.setProperty("assignedTo", asset.getAssignedTo());
            assetVertex.save();

            System.out.println("Asset saved successfully!");
        } catch (Exception e) {
            System.err.println("Error saving asset: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public List<AssetDef> getAllAssets() {
        List<AssetDef> assets = new ArrayList<>();
        String query = "SELECT * FROM AssetDef";

        try (ODatabaseSession db = OrientDBUtil.getSession(); var resultSet = db.query(query)) {
            while (resultSet.hasNext()) {
                var element = resultSet.next().getElement().orElse(null);
                if (element instanceof com.orientechnologies.orient.core.record.impl.ODocument assetDoc) {
                    AssetDef asset = new AssetDef();
                    asset.setId(assetDoc.getProperty("id"));  // Unique ID (UUID)
                    asset.setName(assetDoc.getProperty("name"));
                    asset.setCategory(assetDoc.getProperty("category"));
                    asset.setDescription(assetDoc.getProperty("description"));
                    asset.setPurchaseDate(assetDoc.getProperty("purchaseDate"));
                    asset.setStatus(assetDoc.getProperty("status"));
                    asset.setAssignedTo(assetDoc.getProperty("assignedTo"));
                    assets.add(asset);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching assets: " + e.getMessage());
            e.printStackTrace();
        }
        return assets;
    }

    
    
    
    public AssetDef getAssetById(String id) {
        System.out.println("Fetching asset with ID: " + id);

        try (ODatabaseSession db = OrientDBUtil.getSession()) {
            if (db.getClass("AssetDef") == null) {
                db.createVertexClass("AssetDef");
            }

            String query = "SELECT FROM AssetDef WHERE id = ?";
            try (OResultSet resultSet = db.query(query, id)) {
                if (resultSet.hasNext()) {
                    OElement element = resultSet.next().getElement().orElse(null);

                    if (element == null || !(element instanceof ODocument assetDoc)) {
                        System.out.println("Asset not found or not an ODocument.");
                        return null;
                    }

                    AssetDef asset = new AssetDef();
                    asset.setId(assetDoc.getProperty("id"));
                    asset.setName(assetDoc.getProperty("name"));
                    asset.setCategory(assetDoc.getProperty("category"));
                    asset.setDescription(assetDoc.getProperty("description"));
                    Object purchaseDateObj = assetDoc.getProperty("purchaseDate");
                    asset.setPurchaseDate(purchaseDateObj instanceof Date ? (Date) purchaseDateObj : null);
                    asset.setStatus(assetDoc.getProperty("status"));
                    asset.setAssignedTo(assetDoc.getProperty("assignedTo"));
                    System.out.println("Asset found: " + asset.getName());
                    return asset;
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching asset: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("No asset found with ID: " + id);
        return null;
    }

    public boolean linkComponentToAsset(String assetId, String componentId) {
        System.out.println("Starting OrientDB connection...");

        try (ODatabaseSession db = OrientDBUtil.getSession()) {  
            if (db.getClass("HAS_COMPONENT") == null) {
                db.createEdgeClass("HAS_COMPONENT");
            }

            String assetQuery = "SELECT FROM AssetDef WHERE id = ?";
            String componentQuery = "SELECT FROM ComponentDef WHERE id = ?";

            try (var assetResult = db.query(assetQuery, assetId);
                 var componentResult = db.query(componentQuery, componentId)) {

                if (assetResult.hasNext() && componentResult.hasNext()) {
                    var assetDoc = assetResult.next().getElement().orElse(null);
                    var componentDoc = componentResult.next().getElement().orElse(null);

                    if (assetDoc != null && componentDoc != null) {
                        ORecord assetRecord = db.load(assetDoc.getIdentity());
                        ORecord componentRecord = db.load(componentDoc.getIdentity());

                        if (assetRecord instanceof OVertex assetVertex && componentRecord instanceof OVertex componentVertex) {
                            db.begin();
                            OEdge edge = db.newEdge(assetVertex, componentVertex, "HAS_COMPONENT");
                            edge.save();
                            db.commit();
                            System.out.println("Component linked successfully!");
                            return true;
                        } else {
                            System.err.println("Asset or Component is not a valid vertex.");
                        }
                    } else {
                        System.err.println("Asset or Component not found in the database.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error linking component: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
