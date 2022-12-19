package database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "RoutesData")
public class RoutesEntity {
    @PrimaryKey(autoGenerate = true)
    public int route_id;

    @ColumnInfo(name = "routes_list")
    public String routes;

    @ColumnInfo(name = "location")
    public String location;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public RoutesEntity( String routes, String location) {
        this.routes = routes;
        this.location = location;
    }
}
