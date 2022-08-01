package co.edu.udea.compumovil.certificacion.tasks;

import co.edu.udea.compumovil.certificacion.userinterfases.FormularioPersona;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.ClickOnTarget;

public class ConfirmarFormulario implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(new ClickOnTarget(FormularioPersona.ANADIR_PERSONA_BOTON));
    }

    public static ConfirmarFormulario enviarDatos(){
        return Tasks.instrumented(ConfirmarFormulario.class);
    }


}
