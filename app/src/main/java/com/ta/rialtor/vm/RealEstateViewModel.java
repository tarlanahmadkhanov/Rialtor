package com.ta.rialtor.vm;

import android.app.Application;

import com.ta.rialtor.model.LoadingStatus;
import com.ta.rialtor.model.RealEstate;
import com.ta.rialtor.model.RealEstateDetails;
import com.ta.rialtor.repo.RealEstatesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class RealEstateViewModel extends AndroidViewModel {
    private RealEstatesRepository realEstatesRepository;

    public RealEstateViewModel(@NonNull Application application) {
        super(application);
        realEstatesRepository = RealEstatesRepository.getInstance(application);
    }

    public LiveData<List<RealEstate>> getAllRealEstates() {
        return realEstatesRepository.getAllRealEstates();
    }

    public LiveData<List<RealEstateDetails>> getEstateDetails(String id) {
        return realEstatesRepository.getEstateDetails(id);
    }

    public LiveData<LoadingStatus> getLoadingStatus(int scope) {
        return realEstatesRepository.getLoadingStatusLiveData(scope);
    }


}
