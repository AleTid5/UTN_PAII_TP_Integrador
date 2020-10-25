package src.Services.Entities;

import java.util.Arrays;
import java.util.List;

import src.Models.Step;

public abstract class GuideService {
    private static final List<Step> stepList = Arrays.asList(
            new Step("Paso 1", "Lorem ipsum"),
            new Step("Paso 2", "Lorem ipsum"),
            new Step("Paso 3", "Lorem ipsum"),
            new Step("Paso 4", "Lorem ipsum"),
            new Step("Paso 5", "Lorem ipsum"),
            new Step("Paso 6", "Lorem ipsum")
    );

    public static List<Step> getSteps() {
        return stepList;
    }
}
