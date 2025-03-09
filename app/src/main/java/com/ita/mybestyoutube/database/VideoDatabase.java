package com.ita.mybestyoutube.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.util.Log;

import com.ita.mybestyoutube.dao.VideoDao;
import com.ita.mybestyoutube.pojos.Video;

@Database(entities = {Video.class}, version = 1, exportSchema = false)
public abstract class VideoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "videos";

    public static VideoDatabase getDb(Context context) {
        VideoDatabase db = Room.databaseBuilder(context,VideoDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        Log.i("MyBestYoutube", "Database created");
        return db;
    }

    public abstract VideoDao videoDao();
}
