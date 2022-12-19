package database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoutesEntity.class, LocationEntity.class},version = 1,exportSchema = false)
public abstract class RouteDatabase extends RoomDatabase {
    public abstract RoutesDao routesDao();
    public  abstract LocationDao locationDao();
}
//    private static RouteDatabase INSTANCE;

//    public static RouteDatabase getDbInstance()
//    {
//        if(INSTANCE ==null)
//        {
//            INSTANCE = Room.databaseBuilder(MyApplication.getInstance(),RouteDatabase.class,"ROUTES_DATA")
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return INSTANCE;
//    }
//}

