package li.emily.flagquiz.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import li.emily.flagquiz.AsyncTasks.UpdateCountryAsyncTask;
import li.emily.flagquiz.Model.Country;
import li.emily.flagquiz.Model.CountryDB;
import li.emily.flagquiz.Model.QuestionAnswerDB;
import li.emily.flagquiz.R;

import static li.emily.flagquiz.RoomDatabase.DatabaseSingleton.db;

public class QuestionFragment extends Fragment {


    private ImageView flagImage;
    private Button A;
    private Button B;
    private Button C;
    private Button D;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.question_fragment, container, false);

        return root;
    }

    public void onViewCreated(View v, Bundle savedInstanceState){
        A = v.findViewById(R.id.id_a);
        B = v.findViewById(R.id.id_b);
        C = v.findViewById(R.id.id_c);
        D = v.findViewById(R.id.id_d);
        flagImage = v.findViewById(R.id.id_flag);

        // gets the correct answer
        final Country correctCountry = CountryDB.correctCountries.remove(0);

        String url = correctCountry.getFlag();
        Uri uri = Uri.parse(url);

        // gets the flag image and sets it in the imageview
        GlideToVectorYou.justLoadImage(getActivity(), uri, flagImage);

        // gets the three incorrect answers
        List<Country> incorrectCountries = new ArrayList<Country>();
        List<Country> dbIncorrectCountries = CountryDB.incorrectCountries;
        incorrectCountries.add(dbIncorrectCountries.remove(0));
        incorrectCountries.add(dbIncorrectCountries.remove(0));
        incorrectCountries.add(dbIncorrectCountries.remove(0));

        int correctIndex = (int) (Math.random() * 4);
        List<Button> buttons = Arrays.asList(A,B,C,D);
        int i = 0;

        for(Button button : buttons){
            if(i == correctIndex){
                button.setText(correctCountry.getName());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        QuestionAnswerDB.latestAnswer = true;
                        QuestionAnswerDB.correctChoices.add(correctCountry);
                        // updates the DB, signalling that a correct answer has been chosen
                        new UpdateCountryAsyncTask(db,1,1).execute(correctCountry);
                        getAnswerFragment();
                    }
                });
            } else {
                final Country incorrectChoice = incorrectCountries.remove(0);
                button.setText(incorrectChoice.getName());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        QuestionAnswerDB.latestAnswer = false;
                        QuestionAnswerDB.incorrectChoices.add(incorrectChoice);
                        QuestionAnswerDB.choice = incorrectChoice.getName();
                        QuestionAnswerDB.correct = correctCountry.getName();
                        // updates the DB, signalling that an incorrect answer has been chosen
                        new UpdateCountryAsyncTask(db,1,0).execute(correctCountry);
                        getAnswerFragment();
                    }
                });
            }
            i++;
        }
    }

    public void getAnswerFragment(){
        Fragment aFragment = new AnswerFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_fragment, aFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}