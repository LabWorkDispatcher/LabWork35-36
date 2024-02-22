package com.example.laba30.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.laba30.databases.PDataEntity;
import com.example.laba30.databases.PDataRepository;

import java.util.List;

public class PDataViewModel extends AndroidViewModel {
    private PDataRepository mRepository;
    private LiveData<List<PDataEntity>> mAllData;

    public PDataViewModel(Application application) {
        super(application);
        mRepository = new PDataRepository(application);
        mAllData = mRepository.getAllData();
    }

    public LiveData<List<PDataEntity>> getAllData() {
        return mAllData;
    }

    public void insert(String pData) {
        mRepository.insert(new PDataEntity(pData));
    }
}
