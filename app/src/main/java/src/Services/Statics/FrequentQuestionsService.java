package src.Services.Statics;

import java.util.Arrays;
import java.util.List;

import src.Models.Step;

public abstract class FrequentQuestionsService {
    private static final List<Step> stepList = Arrays.asList(
            new Step("¿Debo establecer contacto con una persona que este en un grupo?", "No. La razon es por motivos de seguridad, no podria controlar todas las situaciones imprevistas si llegasen a generarse en un grupo de personas."),
            new Step("¿Puedo acercarme estando acompañado?", "Si puede, pero el acompañante debera mantener algunos metros de distancia por seguridad y tambien para no atemorizar al individuo asistido."),
            new Step("¿Puedo hacer un llamado en medio de una asistencia?", "Si puede, pero debe preguntar si a la persona asistida, no le molesta que haga un llamado. Muchas veces, estas personas son desconfiadas y creen que podrian llamar a la policia y como consencuencia, podrian desencadenar una reaccion negativa por parte del asistido."),
            new Step("¿Puedo ofrecer dinero?", "Nunca hay que darles dinero (por eso resaltamos en ofrecerle comida, gaseosa, agua o algo caliente). Darles dinero, fomentaría la necesidad de recurrir a bebidas alcohólicas u drogas."),
            new Step("¿Si me ofrecen bebida u comida, puedo aceptarla?", "No debe beber de la misma botella/vaso de ellos. El motivo es por desconocimiento del cuadro clinico de la persona y los riesgos de exponerse a enfermedades contagiosas."),
            new Step("¿Puedo ofrecer llevarlo a mi casa?", "No. Seria un riesgo para usted, por si la persona llegase a reaccionar de forma negativa y con conocimiento de su vivienda"),
            new Step("¿Si me pide el telefono, se lo debo prestar?", "No se debe ofrecer algún teléfono para que ellos mismos puedan hacer llamadas porque se desconoce mucho de la persona asistida."),
            new Step("¿Puedo ofrecerle que suba a mi auto?", "Si, pero solo si usted tiene un acompañante.")
    );

    public static List<Step> getSteps() {
        return stepList;
    }
}
