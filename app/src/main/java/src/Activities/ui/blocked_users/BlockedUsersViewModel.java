package src.Activities.ui.blocked_users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.User;
import src.Services.Entities.UserService;

public class BlockedUsersViewModel extends ViewModel {
    private static MutableLiveData<List<User>> liveUserList = new MutableLiveData<>();

    public BlockedUsersViewModel() {
        liveUserList.setValue(UserService.getBlockedUserList());
    }

    public LiveData<List<User>> getBlockedUserList() {
        return liveUserList;
    }
}