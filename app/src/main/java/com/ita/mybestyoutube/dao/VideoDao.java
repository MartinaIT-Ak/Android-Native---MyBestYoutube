package com.ita.mybestyoutube.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ita.mybestyoutube.pojos.Video;

import java.util.List;

@Dao
public interface VideoDao {
    @Query("SELECT * FROM videos ORDER BY favori DESC")
    List<Video> getAll();

    @Query("SELECT * FROM videos WHERE id IN (:pVideoIds)")
    List<Video> loadAllByIds(long[] pVideoIds);

    @Query("SELECT * FROM videos WHERE titre LIKE :pTitre LIMIT 1")
    Video findByName(String pTitre);

    @Query("SELECT * FROM videos WHERE favori = :pIsFavori")
    Video findFavori(boolean pIsFavori);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Video... videos);

    @Delete
    void delete(Video video);

    @Update
    public void update(Video... videos);
}