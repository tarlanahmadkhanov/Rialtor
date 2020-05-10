package com.ta.rialtor.db;

import android.content.Context;
import android.os.AsyncTask;

import com.ta.rialtor.model.RealEstate;
import com.ta.rialtor.model.RealEstateDetails;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {RealEstate.class, RealEstateDetails.class}, version = 2, exportSchema = false)
public abstract class Db extends RoomDatabase {
    public abstract RealEstateDao realEstateDao();

    private static Db instance;

    public static Db getDatabase(final Context context) {
        if (instance == null) {
            synchronized (Db.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            Db.class, "real_estate_db")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(instance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RealEstateDao dao;

        public PopulateDbAsync(Db db) {
            this.dao = db.realEstateDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();

            for (int i = 0; i < 20; i++) {
                RealEstate realEstate = new RealEstate(Integer.toString(i), i + " main image", i + " description");
                dao.insert(realEstate);
                for (int j = 0; j < 5; j++) {
                    RealEstateDetails realEstateDetails = new RealEstateDetails(Integer.toString(i) + Integer.toString(j), Integer.toString(i), i + " image " + j);
                    dao.insertDetail(realEstateDetails);
                }
            }

            return null;
        }
    }

}
