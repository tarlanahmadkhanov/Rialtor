package com.ta.rialtor.repo;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.ta.rialtor.db.Db;
import com.ta.rialtor.db.RealEstateDao;
import com.ta.rialtor.model.LoadingStatus;
import com.ta.rialtor.model.RealEstate;
import com.ta.rialtor.model.RealEstateAPIService;
import com.ta.rialtor.model.RealEstateDetails;
import com.ta.rialtor.retrofit.Api;
import com.ta.rialtor.retrofit.RetrofitGenerator;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RealEstatesRepository {
    private final String TAG = "RealEstatesRepository";
    private RealEstateDao dao;
    private MutableLiveData<LoadingStatus> loadingStatusLiveData;
    private LoadingStatus loadingStatus;
    private static RealEstatesRepository instance;


    private RealEstatesRepository(Application application) {
        Log.d(TAG, "constructor");
        Db db = Db.getDatabase(application);
        dao = db.realEstateDao();
        loadingStatus = new LoadingStatus(false, "ewo ne nachali");
        loadingStatusLiveData = new MutableLiveData<>();
        loadingStatusLiveData.setValue(loadingStatus);
    }

    public static RealEstatesRepository getInstance(Application application) {
        if (instance == null) {
            instance = new RealEstatesRepository(application);
        }
        return instance;
    }

    public LiveData<LoadingStatus> getLoadingStatusLiveData(int scope) {
        Log.d(TAG, "getLoadingStatusLiveData");
        getEstatesFromApi(scope);
        return loadingStatusLiveData;
    }

    public LiveData<List<RealEstate>> getAllRealEstates() {
        return dao.getAllEstates();
    }

    public LiveData<List<RealEstateDetails>> getEstateDetails(String id) {
        return dao.getEstateDetails(id);
    }


    public void insert(List<RealEstateAPIService> realEstateAPIServices) {
        Log.d(TAG, "insert");
        new insertAsyncTask(dao, realEstateAPIServices).execute();
    }

    public void getEstatesFromApi(int scope) {
        Log.d(TAG, "getEstatesFromApi");
        Api api = RetrofitGenerator.getRetrofitInstance().create(Api.class);
        Call<List<RealEstateAPIService>> estates = api.getEstates(scope);
        estates.enqueue(new Callback<List<RealEstateAPIService>>() {
            @Override
            public void onResponse(Call<List<RealEstateAPIService>> call, Response<List<RealEstateAPIService>> response) {
                Log.d(TAG, "getEstatesFromApi - onResponse");
                if (response.isSuccessful() & response.body() != null) {
                    Log.d(TAG, "getEstatesFromApi - onResponse - then");
                    List<RealEstateAPIService> realEstateAPIServices = response.body();
                    insert(realEstateAPIServices);
                } else {
                    Log.d(TAG, "getEstatesFromApi - onResponse - else");
                }
                loadingStatus.setLoaded(true);
                loadingStatus.setStatusDesc("zagruzka zavershena");
                loadingStatusLiveData.setValue(loadingStatus);
            }

            @Override
            public void onFailure(Call<List<RealEstateAPIService>> call, Throwable t) {
                Log.d(TAG, "onFailure");
                loadingStatus.setLoaded(true);
                loadingStatus.setStatusDesc("nixrena ne poluchilos");
                loadingStatusLiveData.setValue(loadingStatus);
            }
        });
    }

    private static class insertAsyncTask extends AsyncTask<Void, Void, Void> {

        private RealEstateDao asyncTaskDao;
        private List<RealEstateAPIService> realEstateAPIServices;

        public insertAsyncTask(RealEstateDao asyncTaskDao, List<RealEstateAPIService> realEstateAPIServices) {
            this.asyncTaskDao = asyncTaskDao;
            this.realEstateAPIServices = realEstateAPIServices;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.deleteAllDetails();
            asyncTaskDao.deleteAll();

            for (int i = 0; i < realEstateAPIServices.size(); i++) {
                asyncTaskDao.insert(new RealEstate(realEstateAPIServices.get(i).getId(), realEstateAPIServices.get(i).getMainImage(), realEstateAPIServices.get(i).getDesc()));

                for (int j = 0; j < realEstateAPIServices.get(i).getDetails().size(); j++) {
                    asyncTaskDao.insertDetail(new RealEstateDetails(Integer.toString(i) + Integer.toString(j), realEstateAPIServices.get(i).getId(), realEstateAPIServices.get(i).getDetails().get(j)));
                }


            }

            return null;
        }
    }

}
