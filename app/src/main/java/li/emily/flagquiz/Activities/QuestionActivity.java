package li.emily.flagquiz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import li.emily.flagquiz.Fragments.QuestionFragment;
import li.emily.flagquiz.Model.CountryDB;
import li.emily.flagquiz.R;

public class QuestionActivity extends AppCompatActivity {

    QuestionFragment qFragment;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        qFragment = new QuestionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.question_fragment, qFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



    public void onClickNextQuestion(View v){
        // after five questions, return to the main menu
        if(CountryDB.correctCountries.size() == 0) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            qFragment = new QuestionFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.question_fragment, qFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}
