package co.edu.udea.compumovil.certificacion.tasks;

import co.edu.udea.compumovil.certificacion.userinterfases.ListadoPersonas;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.ClickOnTarget;

public class Inicializar implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(new ClickOnTarget(ListadoPersonas.NUEVA_PERSONA_BOTON));
    }

    public static Inicializar crearPersona(){
        return Tasks.instrumented(Inicializar.class);
    }
}
