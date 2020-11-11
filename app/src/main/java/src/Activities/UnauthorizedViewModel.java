package src.Activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import src.Models.User;

public class UnauthorizedViewModel extends ViewModel {
    protected static MutableLiveData<User> liveUser = new MutableLiveData<>();

    public LiveData<User> getLiveUser() {
        return liveUser;
    }

    public static void onUserChange(User user) {
        liveUser.postValue(user);
    }
}
