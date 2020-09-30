package src.Activities.ui.manage_history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ManageHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is history manager fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}