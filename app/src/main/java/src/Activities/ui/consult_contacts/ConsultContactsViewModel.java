package src.Activities.ui.consult_contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConsultContactsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ConsultContactsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contacts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}