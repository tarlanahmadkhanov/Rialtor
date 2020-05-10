package com.ta.rialtor.db;

import com.ta.rialtor.model.RealEstate;
import com.ta.rialtor.model.RealEstateDetails;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RealEstateDao {

    @Insert
    void insert(RealEstate realEstate);

    @Query("delete from real_estates")
    void deleteAll();

    @Query("delete from real_estate_details")
    void deleteAllDetails();

    @Query("select * from real_estates")
    LiveData<List<RealEstate>> getAllEstates();

    @Query("select * from real_estate_details where estateId = :id")
    LiveData<List<RealEstateDetails>> getEstateDetails(String id);

    @Insert
    void insertDetail(RealEstateDetails realEstateDetails);
}
