package com.ta.rialtor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "real_estates")
public class RealEstate {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;
    private String mainImage;
    private String desc;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public RealEstate(String id, String mainImage, String desc) {
        this.id = id;
        this.mainImage = mainImage;
        this.desc = desc;

    }
}
