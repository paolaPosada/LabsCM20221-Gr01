package co.edu.udea.compumovil.certificacion.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class VisualizarLista implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        Boolean validar = true;
        return validar;
    }

    public static VisualizarLista validarPersona() {
        return new VisualizarLista();
    }
}
