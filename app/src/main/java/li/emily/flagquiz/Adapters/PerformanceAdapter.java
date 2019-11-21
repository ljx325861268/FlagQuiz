package li.emily.flagquiz.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import li.emily.flagquiz.Model.Performance;
import li.emily.flagquiz.R;

public class PerformanceAdapter extends RecyclerView.Adapter<PerformanceAdapter.PerformanceViewHolder>{

    private List<Performance> performance;

    public PerformanceAdapter(List<Performance> performance){
        this.performance = performance;
    }

    @NonNull
    @Override
    public PerformanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.performance_list_item,parent,false);
        return new PerformanceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PerformanceViewHolder holder, int position) {
        Performance perf = performance.get(position);
        holder.name.setText(perf.getSubregion());
        holder.unattempted.setText("Unattempted: " + perf.getUnattempted());
        holder.correct.setText("Correct: " + perf.getCorrect());
        holder.incorrect.setText("Incorrect: " + perf.getWrong());
    }

    @Override
    public int getItemCount() {
        return performance.size();
    }


    public class PerformanceViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView unattempted;
        public TextView correct;
        public TextView incorrect;
        public PerformanceViewHolder(View v){
            super(v);
            this.name = (TextView) v.findViewById(R.id.performance_list_item_name);
            this.unattempted = (TextView) v.findViewById(R.id.unattempted);
            this.correct = (TextView) v.findViewById(R.id.correct);
            this.incorrect = (TextView) v.findViewById(R.id.incorrect);
        }
    }
}
