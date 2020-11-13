package src.Activities.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import src.Services.Entities.DashboardService;

public class DashboardViewModel extends ViewModel {
    private static Integer liveHistoryReportsTotal;
    private static Integer liveAlertsTotal;
    private static Integer liveBlockedUsersTotal;
    private static MutableLiveData<Integer> liveTotalCount = new MutableLiveData<>();

    public DashboardViewModel() {
        liveTotalCount.setValue(null);
        DashboardService.initializeDashboard();
    }
}