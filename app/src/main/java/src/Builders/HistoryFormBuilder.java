package src.Builders;

import android.widget.TextView;

import src.Models.History;
import src.Validators.DateValidator;
import src.Validators.NumberValidator;

public class HistoryFormBuilder {
    private static History history;
    private static Integer reportIndexControl;

    public HistoryFormBuilder() {
        history = new History();
        reportIndexControl = 0;
    }

    public HistoryFormBuilder setNameAndLastName(TextView inputName) throws Exception {
        String name = inputName.getText().toString().trim();

        if (!name.replace(" ", "").chars().allMatch(Character::isLetter)) {
            throw new Exception("El nombre solo puede contener letras");
        }

        if (name.length() != 0) {
            reportIndexControl++;
            history.setNameAndLastName(name);
        }

        return this;
    }
    public HistoryFormBuilder setDNI(TextView inputDNI) throws Exception {
        String textDNI = inputDNI.getText().toString().trim();

        if (textDNI.length() != 0) {
            history.setDNI(NumberValidator.validateDNI(textDNI));
            reportIndexControl++;
        }

        return this;
    }
    public HistoryFormBuilder setBornDate(TextView inputBornDate) throws Exception {
        String textBornDate = inputBornDate.getText().toString().trim();

        if (textBornDate.length() != 0) {
            history.setBornDate(DateValidator.validateDateNotGreaterThanToday(textBornDate));
            reportIndexControl++;
        }

        return this;
    }
    public HistoryFormBuilder setPhoneNumber(TextView inputPhoneNumber) throws Exception {
        String textPhoneNumber = inputPhoneNumber.getText().toString().replace(" ", "");

        if (textPhoneNumber.length() != 0) {
            if (textPhoneNumber.length() < 8) {
                throw new Exception("El numero telefónico debe contener como mínimo 8 caracteres");
            }

            history.setPhoneNumber(textPhoneNumber);
            reportIndexControl++;
        }

        return this;
    }
    public HistoryFormBuilder setObservations(TextView inputObservations) {
        String textObservations = inputObservations.getText().toString().trim();

        if (textObservations.length() != 0) {
            history.setObservations(textObservations);
            reportIndexControl++;
        }

        return this;
    }

    public History build() throws Exception {
        if (reportIndexControl.equals(0)) {
            throw new Exception("Debe completar al menos un campo del formulario");
        }

        return history;
    }
}
