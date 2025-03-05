package com.optimas.ams.model;

import java.util.Date;

public class AssetDef {
    private String id;  // Unique ID (UUID)
    private String name;
    private String category;
    private String description;
    private Date purchaseDate;
    private String status;
    private String assignedTo;

    
    public AssetDef() {}

    
    public AssetDef(String id, String name, String category, String description, Date purchaseDate, String status, String assignedTo) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.assignedTo = assignedTo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "AssetDef{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", status='" + status + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                '}';
    }
}
