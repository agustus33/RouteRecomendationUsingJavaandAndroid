package database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface LocationDao {
    @Query("SELECT * FROM finaldata")
    List<LocationEntity> getAll();

    @Insert
   void insertLocations(LocationEntity...locations);

    @Query("SELECT updated_list From FinalData")
    List<String> updatedList();

    @Query("SELECT * FROM FinalData ORDER BY gap DESC;")
    List<LocationEntity> orderedRoutes();

}

