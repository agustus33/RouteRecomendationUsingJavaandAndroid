package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FinalData")
public class LocationEntity {
        @PrimaryKey(autoGenerate = true)
        public int route_id;

        @ColumnInfo(name = "gap")
        public int gap;

        @ColumnInfo(name = "updated_list")
        public String updatedList;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public String getUpdatedList() {
        return updatedList;
    }

    public void setUpdatedList(String updatedList) {
        this.updatedList = updatedList;
    }

    public LocationEntity(int gap, String updatedList) {
        this.gap = gap;
        this.updatedList = updatedList;
    }
}
