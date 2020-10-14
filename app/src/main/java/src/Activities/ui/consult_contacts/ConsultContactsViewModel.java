package src.Activities.ui.consult_contacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import src.Models.Contact;
import src.Services.Entities.ContactService;

public class ConsultContactsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private static MutableLiveData<List<Contact>> liveContactList = new MutableLiveData<>();

    public ConsultContactsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Consultar contactos");
        liveContactList.setValue(ContactService.findContacts());
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Contact>> getContactList() {
        return liveContactList;
    }
}