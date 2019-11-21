package li.emily.flagquiz.Model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CountryDAO {
    @Query("Select * from CountryRow")
    List<CountryRow> getCountries();

    @Query("Select * from CountryRow where name = :name")
    CountryRow getCountryByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CountryRow country);

    @Query("Select count(*) from CountryRow")
    int count();

    @Query("select subregion, attempted, correct, count(*) as count from CountryRow\n" +
            "group by subregion, attempted, correct")
    List<SubregionRow> getSubregionResults();

    @Query("UPDATE CountryRow SET attempted = 1 where name = :name")
    void updateCorrect(String name);

    @Update
    void updateIncorrect(CountryRow country);

    @Query("delete from CountryRow where name is not null")
    void deleteTable();
}