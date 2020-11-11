package src.Builders;

import android.widget.TextView;

import src.Models.Alert;
import src.Services.Entities.UserSessionService;

public class AlertBuilder {
    private static Alert alert;

    public AlertBuilder() {
        alert = new Alert();
    }

    public AlertBuilder setNameAndLastName(TextView inputName) throws Exception {
        String name = inputName.getText().toString().trim();

        if (!name.replace(" ", "").chars().allMatch(Character::isLetter)) {
            throw new Exception("El nombre solo puede contener letras");
        }

        alert.setNameAndLastName(name);

        return this;
    }

    public AlertBuilder setPlace(TextView inputPlace) throws Exception {
        String textPlace = inputPlace.getText().toString().trim();

        if (textPlace.length() < 5) {
            throw new Exception("El lugar de encuentro ingresado es inválido");
        }

        alert.setPlace(textPlace);

        return this;
    }

    public AlertBuilder setContact(TextView inputContact) throws Exception {
        String textContact = inputContact.getText().toString().replace(" ", "");

        if (textContact.length() != 0) {
            if (textContact.length() < 8) {
                throw new Exception("El numero telefónico debe contener como mínimo 8 caracteres");
            }

            alert.setContact(textContact);
        }

        return this;
    }

    public AlertBuilder setObservations(TextView inputObservations) {
        String textObservations = inputObservations.getText().toString().trim();

        alert.setObservations(textObservations);

        return this;
    }

    public Alert build() {
        alert.setUserId(UserSessionService.getUser().getId());
        return alert;
    }
}
