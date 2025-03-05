package com.optimas.ams.model;

public class HasComponent {
	private String id;
	private String assetId;
	private String componentId;

	public HasComponent() {
	}

	public HasComponent(String id, String assetId, String componentId) {
		this.id = id;
		this.assetId = assetId;
		this.componentId = componentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Override
	public String toString() {
		return "HasComponent{" + "id='" + id + '\'' + ", assetId='" + assetId + '\'' + ", componentId='" + componentId
				+ '\'' + '}';
	}
}
