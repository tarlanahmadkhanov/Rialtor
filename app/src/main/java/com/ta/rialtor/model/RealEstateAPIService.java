package com.ta.rialtor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RealEstateAPIService {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mainImage")
    @Expose
    private String mainImage;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("details")
    @Expose
    private List<String> details = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}