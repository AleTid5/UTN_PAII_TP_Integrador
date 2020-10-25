package src.Activities.ui.consult_guides;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Step;
import src.Services.Entities.GuideService;

public class ConsultGuidesViewModel extends ViewModel {

    private static MutableLiveData<List<Step>> liveStepList = new MutableLiveData<>();

    public ConsultGuidesViewModel() {
        liveStepList.setValue(GuideService.getSteps());
    }

    public LiveData<List<Step>> getStepList() {
        return liveStepList;
    }
}