package li.emily.navbar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import li.emily.navbar.R;

public class AnswerFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);


        View root = inflater.inflate(R.layout.answer_fragment, container, false);
        //final TextView textView = root.findViewById(R.id.id_question_text);

/*
        questionViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
*/
        return root;
    }


}
