package li.emily.navbar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import li.emily.navbar.Fragments.AnswerFragment;
import li.emily.navbar.Fragments.QuestionFragment;
import li.emily.navbar.R;

public class QuestionActivity extends AppCompatActivity {

    QuestionFragment qFragment;
    AnswerFragment aFragment;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        System.out.print("abc");

        qFragment = new QuestionFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_fragment, qFragment);
        fragmentTransaction.commit();
    }

    public void onClickA(View v){
        selectionClick(0);
    }

    public void onClickB(View v){
        selectionClick(1);
    }

    public void onClickC(View v){
        selectionClick(2);
    }

    public void onClickD(View v){
        selectionClick(3);
    }

    public void selectionClick(int selection){
        aFragment = new AnswerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.question_fragment, aFragment);
        fragmentTransaction.commit();
    }

    public void onClickNextQuestion(View v){

    }
}
