package li.emily.navbar.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.caverock.androidsvg.SVG;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.gson.Gson;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import li.emily.navbar.Model.AppDatabase;
import li.emily.navbar.Model.Country;
import li.emily.navbar.Model.CountryDB;
import li.emily.navbar.Model.EasyCountry;
import li.emily.navbar.Model.QuestionAnswerDB;
import li.emily.navbar.R;
import li.emily.navbar.ui.dashboard.DashboardViewModel;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class QuestionFragment extends Fragment {


    private ImageView flagImage;
    private Button A;
    private Button B;
    private Button C;
    private Button D;
    OkHttpClient httpClient = null;


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
        final AppDatabase db = AppDatabase.getInstance(getContext());
        for(Button button : buttons){
            if(i == correctIndex){
                button.setText(correctCountry.getName());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        QuestionAnswerDB.latestAnswer = true;
                        QuestionAnswerDB.correctChoices.add(correctCountry);
                        db.countryDao().updateCorrect(correctCountry.getName());
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
                        db.countryDao().updateCorrect(incorrectChoice.getName());
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