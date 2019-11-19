package li.emily.navbar.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class SubregionRow {

    public String subregion;
    public boolean attempted;
    public boolean correct;

    public SubregionRow(String subregion) {
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