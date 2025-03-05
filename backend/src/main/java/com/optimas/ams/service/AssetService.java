package com.optimas.ams.service;

import com.optimas.ams.exception.AssetAlreadyExistsException;
import com.optimas.ams.exception.AssetNotFoundException;
import com.optimas.ams.exception.CustomException;
import com.optimas.ams.exception.DatabaseException;
import com.optimas.ams.exception.NoAssetsFoundException;
import com.optimas.ams.model.AssetDef;
import com.optimas.ams.repository.AssetRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssetService {

	private static AssetRepository assetRepository;

	public AssetService(AssetRepository assetRepository) {
		this.assetRepository = assetRepository;
	}

	public void saveAsset(AssetDef asset) {
		   try {
		        asset.setId(UUID.randomUUID().toString()); // Generate unique UUID
		        assetRepository.save(asset);
		        System.out.println("Asset saved: " + asset);
		    } catch (DataIntegrityViolationException e) {
		        throw new AssetAlreadyExistsException("Asset with ID already exists.");
		    } catch (Exception e) {
		        throw new DatabaseException("Failed to save asset due to an unexpected error.");
		    }
	}

	public List<AssetDef> getAllAssets() {
	    List<AssetDef> assets = assetRepository.getAllAssets();
	    if (assets.isEmpty()) {
	        throw new NoAssetsFoundException("Assets not found");  
	    }
	    return assets;
	}

	
	
	public AssetDef getAssetById(String id) {
		AssetDef asset= assetRepository.getAssetById(id);
		if (asset == null) {
	        throw new AssetNotFoundException(id);
	    }
	    return asset;
	}

	public boolean linkComponentToAsset(String assetId, String componentId) {
		 try {
		        boolean linked = assetRepository.linkComponentToAsset(assetId, componentId);
		        if (!linked) {
		            throw new DatabaseException("Failed to link component " + componentId + " to asset " + assetId);
		        }
		        return true;
		    } catch (Exception e) {
		        throw new DatabaseException("Error while linking component: " + e.getMessage());
		    }
	}

}
