package src.Activities.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {
    private static Integer liveHistoryReportsTotal;
    private static Integer liveAlertsTotal;
    private static Integer liveBlockedUsersTotal;
    private static MutableLiveData<Integer> liveTotalCount = new MutableLiveData<>();

}