package li.emily.navbar.Model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity
public class CountryRow {
    @PrimaryKey
    @NonNull public String name;
    public String subregion;
    public boolean attempted;
    public boolean correct;

    public CountryRow(String name, String subregion) {
        this.name = name;
        this.subregion = subregion;
        this.attempted = false;
        this.correct = false;
    }
}


/*
performance will have:
ratings for each region
correct / incorrect / attempted
 */