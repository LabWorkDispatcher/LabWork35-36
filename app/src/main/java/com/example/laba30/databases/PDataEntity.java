package com.example.laba30.databases;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pData_table")
public class PDataEntity {
    public String info_pData = "";

    public PDataEntity(String info_pData) {
        this.info_pData = info_pData;
    }

    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    void setId(int info_id) {
        this.id = info_id;
    }
    int getId() {
        return this.id;
    }

    @NonNull
    @ColumnInfo(name = "pData")
    private String value = info_pData;

    void setValue(String pData) {
        this.value = pData;
    }
    String getValue() {
        return this.value;
    }
}
