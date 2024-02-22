package com.example.laba30.databases;

import static com.example.laba30.data.Constants.APP_DB_PROPER_NAME;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = PDataEntity.class, version = 1, exportSchema = false)
public abstract class PDataDatabase extends RoomDatabase {
    abstract PDataDAO pDataDAO();

    static private PDataDatabase INSTANCE = null;
    static private final int NUMBER_OF_THREADS = 4;

    static ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PDataDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized(PDataDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PDataDatabase.class, APP_DB_PROPER_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static private final Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                PDataDAO dao = INSTANCE.pDataDAO();
                dao.deleteAll();
            });
        }
    };
}
