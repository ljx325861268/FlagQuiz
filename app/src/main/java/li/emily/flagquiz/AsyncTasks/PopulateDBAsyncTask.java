package li.emily.flagquiz.AsyncTasks;

import android.os.AsyncTask;

import java.util.List;

import li.emily.flagquiz.Model.Country;
import li.emily.flagquiz.Model.CountryDAO;
import li.emily.flagquiz.Model.CountryRow;
import li.emily.flagquiz.RoomDatabase.AppDatabase;

// async task that populates the DB given a list of countries as input
public class PopulateDBAsyncTask extends AsyncTask<List<Country>, Void, Void> {

    private CountryDAO dao;

    public PopulateDBAsyncTask(AppDatabase db){
        dao = db.countryDao();
    }

    @Override
    protected Void doInBackground(List<Country>... input) {
        for(Country c : input[0]){
            CountryRow row = new CountryRow(c.getName(), c.getSubregion(),0,0);
            dao.insert(row);
        }
        return null;
    }
}
