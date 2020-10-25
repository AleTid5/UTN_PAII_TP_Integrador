package src.Services.Entities;

import java.util.Arrays;
import java.util.List;

import src.Models.Alert;

public abstract class AlertService {
    private static List<Alert> alertList = Arrays.asList(
            new Alert(),
            new Alert(),
            new Alert()
    );

    public static List<Alert> getAlertList() {
        return alertList;
    }
}
