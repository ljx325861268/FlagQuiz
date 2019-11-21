package li.emily.flagquiz.AsyncTasks;

import android.os.AsyncTask;

import li.emily.flagquiz.Model.Country;
import li.emily.flagquiz.Model.CountryDAO;
import li.emily.flagquiz.Model.CountryRow;
import li.emily.flagquiz.RoomDatabase.AppDatabase;

// async task that updates the DB given a country as input
public class UpdateCountryAsyncTask extends AsyncTask<Country, Void, Void> {

    private CountryDAO dao;
    private int attempted;
    private int correct;

    public UpdateCountryAsyncTask(AppDatabase db, int attempted, int correct){
        dao = db.countryDao();
        this.attempted = attempted;
        this.correct = correct;
    }

    @Override
    protected Void doInBackground(Country... input) {
        Country c = input[0];
        CountryRow row = new CountryRow(c.getName(), c.getSubregion(),attempted,correct);
        dao.insert(row);

        return null;
    }
}
