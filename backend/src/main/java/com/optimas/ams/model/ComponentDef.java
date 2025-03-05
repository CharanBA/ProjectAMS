package com.optimas.ams.model;

import java.util.Date;

public class ComponentDef {
	private String id;
	private String name;
	private String category;
	private String manufacturer;
	private String serialNumber;
	private Date warrantyEnd;

	public ComponentDef() {
	}

	public ComponentDef(String id, String name, String category, String manufacturer, String serialNumber,
			Date warrantyEnd) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.manufacturer = manufacturer;
		this.serialNumber = serialNumber;
		this.warrantyEnd = warrantyEnd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getWarrantyEnd() {
		return warrantyEnd;
	}

	public void setWarrantyEnd(Date warrantyEnd) {
		this.warrantyEnd = warrantyEnd;
	}

	@Override
	public String toString() {
		return "ComponentDef{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", type='" + category + '\''
				+ ", manufacturer='" + manufacturer + '\'' + ", serialNumber='" + serialNumber + '\'' + ", warrantyEnd="
				+ warrantyEnd + '}';
	}
}
