package src.Services.Statics;

import java.util.Arrays;
import java.util.List;

import src.Models.Step;

public abstract class FrequentQuestionsService {
    private static final List<Step> stepList = Arrays.asList(
            new Step("Pregunta 1", "Lorem ipsum"),
            new Step("Pregunta 2", "Lorem ipsum"),
            new Step("Pregunta 3", "Lorem ipsum"),
            new Step("Pregunta 4", "Lorem ipsum"),
            new Step("Pregunta 5", "Lorem ipsum"),
            new Step("Pregunta 6", "Lorem ipsum")
    );

    public static List<Step> getSteps() {
        return stepList;
    }
}
