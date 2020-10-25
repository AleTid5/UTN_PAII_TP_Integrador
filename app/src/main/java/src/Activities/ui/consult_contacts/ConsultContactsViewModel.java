package src.Activities.ui.consult_contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Contact;
import src.Services.Entities.ContactService;

public class ConsultContactsViewModel extends ViewModel {

    private static MutableLiveData<List<Contact>> liveContactList = new MutableLiveData<>();

    public ConsultContactsViewModel() {
        liveContactList.setValue(ContactService.findContacts());
    }

    public LiveData<List<Contact>> getContactList() {
        return liveContactList;
    }
}