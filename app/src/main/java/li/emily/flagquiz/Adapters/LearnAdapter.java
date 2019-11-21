package li.emily.flagquiz.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import li.emily.flagquiz.Model.Country;
import li.emily.flagquiz.R;

public class LearnAdapter extends RecyclerView.Adapter<LearnAdapter.PerformanceViewHolder>{

    private List<Country> performance;
    private onClickListener onClickListener;

    public LearnAdapter(List<Country> performance, onClickListener onClickListener){
        this.performance = performance;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public PerformanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_list_item,parent,false);
        return new PerformanceViewHolder(v, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PerformanceViewHolder holder, int position) {
        Country c = performance.get(position);
        holder.name.setText(c.getName());
    }

    @Override
    public int getItemCount() {
        return performance.size();
    }

    public class PerformanceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public onClickListener onClickListener;
        public PerformanceViewHolder(View v, onClickListener onClickListener){
            super(v);
            this.name = (TextView) v.findViewById(R.id.learn_list_item_name);
            this.onClickListener = onClickListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface onClickListener{
        void onClick(int position);
    }
}
