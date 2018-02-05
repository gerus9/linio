package com.gerus.linio.models;

import com.gerus.linio.models.deserializer.ProductDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by gerus-mac on 03/02/18.
 */

public class FavoriteModel {

    private int id;
    private String name;
    private String description;
    @SerializedName("default")
    private boolean defaultX;
    private Owner owner;
    private Date createdAt;
    private String visibility;

    @JsonAdapter(ProductDeserializer.class)
    private List<Product> products;

    private String accessHash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDefault() {
        return defaultX;
    }

    public void setDefault(boolean defaultX) {
        this.defaultX = defaultX;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getAccessHash() {
        return accessHash;
    }

    public void setAccessHash(String accessHash) {
        this.accessHash = accessHash;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
