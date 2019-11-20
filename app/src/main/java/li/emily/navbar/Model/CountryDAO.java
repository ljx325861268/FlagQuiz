package li.emily.navbar.Model;

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
    CountryRow getCountryByName(long name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CountryRow country);

    @Query("Select count(*) from CountryRow")
    int count();

    @Query("select subregion, attempted, correct  from CountryRow\n" +
            "where subregion = :subregion\n" +
            "group by subregion, attempted, correct")
    List<SubregionRow> getSubregionResults(String subregion);

    @Query("update CountryRow set attempted = 1, correct = 1 where name = :countryName")
    void updateCorrect(String countryName);

    @Update
    void updateIncorrect(CountryRow country);

    @Query("delete from CountryRow where name is not null")
    void deleteTable();
}