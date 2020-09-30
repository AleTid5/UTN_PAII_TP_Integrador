package src.Activities.ui.consult_guides;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultGuidesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultGuidesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is guides fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}