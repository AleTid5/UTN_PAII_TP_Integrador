package src.Activities.ui.blocked_users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import src.Activities.Adapters.BlockedUserAdapter;
import src.Models.User;
import src.Services.Entities.AlertService;
import src.Services.Entities.UserService;

public class BlockedUsersViewModel extends ViewModel {
    private static MutableLiveData<List<User>> liveUserList = new MutableLiveData<>();

    public BlockedUsersViewModel() {
        liveUserList.setValue(new ArrayList<>());
        UserService.fetchBlockedUserList();
    }

    public LiveData<List<User>> getBlockedUserList() {
        return liveUserList;
    }

    public void unblockUsers() {
        List<String> blockedUsers = BlockedUserAdapter.getSelectedUsers();
        List<User> userList = Objects.requireNonNull(liveUserList.getValue()).stream()
                .filter(user -> !blockedUsers.contains(user.getId()))
                .collect(Collectors.toList());

        liveUserList.postValue(userList);

        AlertService.unblockUser(BlockedUserAdapter.getSelectedUsers());
        BlockedUserAdapter.cleanSelectedUsers();
    }

    public static void onAddBlockedUser(User user) {
        try {
            Objects.requireNonNull(liveUserList.getValue()).add(user);
            liveUserList.postValue(liveUserList.getValue());
        } catch (Exception ignored) {}
    }
}