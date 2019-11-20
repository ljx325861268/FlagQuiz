package li.emily.navbar.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity
public class CountryRow {
    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull public String name;

    @ColumnInfo(name = "subregion")
    public String subregion;

    @ColumnInfo(name = "attempted")
    public int attempted;

    @ColumnInfo(name = "correct")
    public int correct;

    public CountryRow(String name, String subregion, int attempted, int correct) {
        this.name = name;
        this.subregion = subregion;
        this.attempted = attempted;
        this.correct = correct;
    }
}


/*
performance will have:
ratings for each region
correct / incorrect / attempted
 */