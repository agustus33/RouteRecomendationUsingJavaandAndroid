package database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import database.RoutesEntity;

@Dao
public interface RoutesDao {

    @Query("SELECT * FROM RoutesData")
    List<RoutesEntity> getAll();

    @Insert
    void insertRoutes(RoutesEntity...routes);

    @Query("SELECT routes_list From RoutesData")
    List<String> getAllRoutes();
}
