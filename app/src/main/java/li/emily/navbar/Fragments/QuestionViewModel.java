package li.emily.navbar.Fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QuestionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is question fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}