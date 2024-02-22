package com.example.laba30.databases;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PDataRepository {
    private PDataDAO pDataDAO;
    private LiveData<List<PDataEntity>> mAllData;

    public PDataRepository(Application application) {
        PDataDatabase db = PDataDatabase.getDatabase(application);
        pDataDAO = db.pDataDAO();
        mAllData = pDataDAO.getSortedDatas();
    }

    public LiveData<List<PDataEntity>> getAllData() {
        return mAllData;
    }

    public void insert(PDataEntity info_pData) {
        PDataDatabase.databaseWriteExecutor.execute(() -> {
            pDataDAO.insert(info_pData);
        });
    }
}
