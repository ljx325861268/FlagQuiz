package li.emily.flagquiz.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import li.emily.flagquiz.Model.QuestionAnswerDB;
import li.emily.flagquiz.R;

public class AnswerFragment extends Fragment {

    private TextView text;
    private Button next;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.answer_fragment, container, false);
        return root;
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        text = v.findViewById(R.id.correctincorrect);
        next = v.findViewById(R.id.id_buttonnext);
        // if the latest answer is correct then display "correct"
        // else show them the correct answer
        String displayText = null;
        if(QuestionAnswerDB.latestAnswer){
            displayText = "Correct!";
        } else {
            displayText = QuestionAnswerDB.choice + " is incorrect!\n" +
                    " The correct answer is " + QuestionAnswerDB.correct;
        }
        text.setText(displayText);
    }
}
