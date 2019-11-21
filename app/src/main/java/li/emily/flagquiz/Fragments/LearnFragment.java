package li.emily.flagquiz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import li.emily.flagquiz.Activities.FlagInformationActivity;
import li.emily.flagquiz.Adapters.LearnAdapter;
import li.emily.flagquiz.Model.Country;
import li.emily.flagquiz.Model.CountryDB;
import li.emily.flagquiz.R;

public class LearnFragment extends Fragment implements LearnAdapter.onClickListener{

    List<Country> listOfCountries = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_learn, container, false);
        return root;
    }

    public void onViewCreated(View v, Bundle savedInstanceState){

        listOfCountries = CountryDB.getCountries();

        // sorts the list of countries by name
        Collections.sort(listOfCountries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        // prepares the recycler view for the learning fragment
        RecyclerView rv = getView().findViewById(R.id.id_learn_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        LearnAdapter adapter = new LearnAdapter(listOfCountries, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), FlagInformationActivity.class);
        Country country = listOfCountries.get(position);
        intent.putExtra("name", country.getName());
        intent.putExtra("flag", country.getFlag());
        startActivity(intent);
    }
}