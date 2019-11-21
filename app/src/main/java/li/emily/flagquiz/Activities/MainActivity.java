package li.emily.flagquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import li.emily.flagquiz.AsyncTasks.PopulateDBAsyncTask;
import li.emily.flagquiz.Model.Country;
import li.emily.flagquiz.Model.CountryDB;
import li.emily.flagquiz.Model.EasyCountry;
import li.emily.flagquiz.R;
import li.emily.flagquiz.RoomDatabase.AppDatabase;
import li.emily.flagquiz.RoomDatabase.DatabaseSingleton;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_learn, R.id.navigation_performance)
                .build();

        // prepares the navigation menu at the bottom
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        db = AppDatabase.getInstance(getApplicationContext());

        // keeps the database as a singleton which can be accessed anywhere
        DatabaseSingleton.db = db;
        callAPI();
    }

    // takes you to the level selection activity
    public void onTakeQuizClick(View v){
        Intent intent = new Intent(getApplicationContext(), LevelSelectionActivity.class);
        startActivity(intent);
    }

    // resets the performance stats
    public void resetStats(View v){
        db.countryDao().deleteTable();
        List<Country> allCountries = CountryDB.getCountries();
        new PopulateDBAsyncTask(db).execute(allCountries);
    }

    public void callAPI() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://restcountries.eu/rest/v2/all";

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Country[] res = new Gson().fromJson(response, Country[].class);
                if(res != null && res.length > 0) {
                    CountryDB.setCountries(res);
                    CountryDB.mimic();

                    // separates out the hard countries from the easy countries
                    HashMap<String,Country> hardCountryMap = CountryDB.getHardCountries();
                    HashMap<String, Country> easyCountryMap = new HashMap<String, Country>();

                    List<String> easyCountries = EasyCountry.getEasyCountries();
                    for(String easyCountry : easyCountries){
                        easyCountryMap.put(easyCountry, hardCountryMap.get(easyCountry));
                        hardCountryMap.remove(easyCountry);
                    }
                    CountryDB.setEasyCountryMap(easyCountryMap);
                }

                int tableCount = db.countryDao().count();
                // do not fill the table if the table already exists
                if(tableCount == 0){
                    List<Country> allCountries = CountryDB.getCountries();
                    new PopulateDBAsyncTask(db).execute(allCountries);
                }
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error: Flags API call has failed");
            }
        };
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
