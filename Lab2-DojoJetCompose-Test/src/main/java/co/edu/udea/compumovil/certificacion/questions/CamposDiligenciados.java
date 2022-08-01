package co.edu.udea.compumovil.certificacion.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CamposDiligenciados implements Question<String> {

    private String campo;

    public CamposDiligenciados(String campo) {
        this.campo = campo;
    }

    @Override
    public String answeredBy(Actor actor) {
        return actor.recall(campo).toString();
    }

    public static CamposDiligenciados validarCampo(String campo) {
        return new CamposDiligenciados(campo);
    }
}
