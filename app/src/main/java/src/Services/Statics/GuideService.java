package src.Services.Statics;

import java.util.Arrays;
import java.util.List;

import src.Models.Step;

public abstract class GuideService {
    private static final List<Step> stepList = Arrays.asList(
            new Step("1. Acercarse", "Si ya ha visto mas de una vez a la persona en el mismo lugar, acerquese con una actitud segura y relajada. Haber visto a la persona en varias ocasiones anteriores, da un criterio de confianza sobre que clase de persona puede ser. Cabe aclarar, que la mayoria de las personas toman de buena manera este acercamiento."),
            new Step("2. Contacto", "Haga contacto en un lugar iluminado y con presencia de otras personas alrededor. El motivo es para tener una seguridad ante situaciones imprevistas."),
            new Step("3. Hablar", "Manteniendo una distancia de aproximadamente 3 metros, trate de entablar una conversacion con la persona para verificar el grado de consciencia sobre el tiempo y contexto que lo rodea. Si la persona responde, trate de indagar mas sobre la situacion que lo puso en ese lugar. Podra comenzar, por ejemplo, con si le permite hablar un rato, dejando en claro que solo quiere prestar ayuda y que no trabaja para ningun ente institucional."),
            new Step("4. Consolar", "Deje en claro que solo desea ayudar. No utilize tonos imperativos, es decir, la persona no debe creer que le estamos tratando de dar ordenes."),
            new Step("5. Confianza", "Debera realizar algunas preguntas para conocer el grado de sensatez y coherencia de la persona. Algunas preguntas que podrian ayudarle son: ¿Quiere que lo ayudemos? ¿Necesita alguna ayuda? ¿Por qué está viviendo en la calle? ¿Tiene familiares o personas cercanas a quien contactar? ¿De qué localidad, ciudad o provincia es? ¿Se acuerda su dirección? ¿Podría darnos algún dato para contactar a alguien?"),
            new Step("6. Datos", "Luego de estas preguntas, deberá intentar conseguir datos como su número DNI, si posee familia, de donde es, si tiene algún contacto para llamar. Estos datos son importantes para facilitar a las organizaciones/instituciones involucrados en agilizar los tramites necesarios para mejorar la condición del individuo asistido."),
            new Step("7. Ofrecer", "Finalmente, podra ofrecerle comida, agua, gaseosas, bebidas calientes, muda de ropa, etc. Si anteriormente, visualizo a la persona en varias ocasiones antes del contacto, tendra una idea de que podria llegar a ofrecerle.")
    );

    public static List<Step> getSteps() {
        return stepList;
    }
}
