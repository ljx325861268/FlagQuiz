package li.emily.flagquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import li.emily.flagquiz.Model.CountryDB;
import li.emily.flagquiz.R;

public class LevelSelectionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_level_layout);
    }

    public void onMediumClick(View v){
        // prepares the medium difficulty questions
        CountryDB.prepareQuestions(true);
        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        startActivity(intent);
    }

    public void onHardClick(View v){
        // prepares the hard difficulty questions
        CountryDB.prepareQuestions(false);
        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        startActivity(intent);
    }

}