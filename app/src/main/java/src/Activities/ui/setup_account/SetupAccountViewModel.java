package src.Activities.ui.setup_account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SetupAccountViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SetupAccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is setup account fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}