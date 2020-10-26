package src.Activities.ui.frequent_questions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Step;
import src.Services.Statics.FrequentQuestionsService;

public class FrequentQuestionsViewModel extends ViewModel {
    private static MutableLiveData<List<Step>> liveStepList = new MutableLiveData<>();

    public FrequentQuestionsViewModel() {
        liveStepList.setValue(FrequentQuestionsService.getSteps());
    }

    public LiveData<List<Step>> getStepList() {
        return liveStepList;
    }
}