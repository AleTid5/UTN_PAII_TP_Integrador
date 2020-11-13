package src.Activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import src.Enums.StatusEnum;

public class StatusViewModel extends ViewModel {
    protected static MutableLiveData<StatusEnum> liveStatus = new MutableLiveData<>();

    public StatusViewModel() {
        liveStatus.setValue(StatusEnum.NO_ACTION);
    }

    public LiveData<StatusEnum> getLiveStatus() {
        return liveStatus;
    }

    public static void onStatusChange(StatusEnum status) {
        liveStatus.postValue(status);
    }
}
