package com.example.laba30.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PDataDAO {
    @Query("SELECT * FROM pData_table ORDER BY pData ASC")
    LiveData<List<PDataEntity>> getSortedDatas();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PDataEntity pData);

    @Query("DELETE FROM pData_table")
    void deleteAll();
}
