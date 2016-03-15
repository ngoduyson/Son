package com.example.sonyama.dayseeson.data.model;

import java.io.Serializable;

/**
 * Created by sonyama on 3/15/16.
 */
public class Channel implements Serializable {
    private int id;
    private String category;
    private String name;
    private String identifier;
    private int type;
    private String color;
    private String siteUrl;
    private String iconUrl;
    private String recipeIconUrl;
    private Boolean authorized;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getRecipeIconUrl() {
        return recipeIconUrl;
    }

    public void setRecipeIconUrl(String recipeIconUrl) {
        this.recipeIconUrl = recipeIconUrl;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
