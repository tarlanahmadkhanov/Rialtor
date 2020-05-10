package com.ta.rialtor.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = RealEstate.class,
        parentColumns = "id",
        childColumns = "estateId",
        onDelete = ForeignKey.CASCADE), tableName = "real_estate_details",
        indices = {@Index("estateId")})

public class RealEstateDetails {
    @PrimaryKey
    @NonNull
    private String detailId;
    private String estateId;
    private String imageUrl;

    public RealEstateDetails(@NonNull String detailId, String estateId, String imageUrl) {
        this.detailId = detailId;
        this.estateId = estateId;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(@NonNull String detailId) {
        this.detailId = detailId;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
