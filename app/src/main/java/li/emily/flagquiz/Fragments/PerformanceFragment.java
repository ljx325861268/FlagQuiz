package li.emily.flagquiz.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import li.emily.flagquiz.Adapters.PerformanceAdapter;
import li.emily.flagquiz.Model.Performance;
import li.emily.flagquiz.Model.SubregionRow;
import li.emily.flagquiz.R;

import static li.emily.flagquiz.RoomDatabase.DatabaseSingleton.db;

public class PerformanceFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.performance_fragment, container, false);
        return root;
    }

    public void onViewCreated(View v, Bundle savedInstnceState){
        List<SubregionRow> subregionsRowList = db.countryDao().getSubregionResults();
        HashMap<String, Performance> performance = getPerformance(subregionsRowList);
        List<Performance> performanceList = new ArrayList(performance.values());

        // sorts performance stats by subRegion
        // e.g. eastern asia / western asia
        Collections.sort(performanceList, new Comparator<Performance>() {
            @Override
            public int compare(Performance o1, Performance o2) {
                return o1.getSubregion().compareTo(o2.getSubregion());
            }
        });


        // prepares the recycler view for the performance fragment
        RecyclerView rv = getView().findViewById(R.id.id_performance_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        PerformanceAdapter adapter = new PerformanceAdapter(performanceList);
        rv.setAdapter(adapter);
    }

    // prepares the text to display for the performance fragment given what has been saved in the DB
    public HashMap<String, Performance> getPerformance(List<SubregionRow> subregionsRowList){
        HashMap<String, Performance> map = new HashMap<>();
        for(SubregionRow row : subregionsRowList){
            if(!map.containsKey(row.subregion)){
                map.put(row.subregion, new Performance(row.subregion));
            }
            Performance performance = map.get(row.subregion);
            if(row.attempted == false){
                performance.setUnattempted(row.count);
            } else {
                if(row.correct == true){
                    performance.setCorrect(row.count);
                } else {
                    performance.setWrong(row.count);
                }
            }
            map.put(row.subregion, performance);
        }
        return map;
    }
}