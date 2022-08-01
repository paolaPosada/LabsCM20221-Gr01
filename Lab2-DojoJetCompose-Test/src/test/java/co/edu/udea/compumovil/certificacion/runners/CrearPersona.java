package co.edu.udea.compumovil.certificacion.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/crear_persona.feature"},
        glue = {"co.edu.udea.compumovil.certificacion.stepdefinitions"},
        snippets = SnippetType.CAMELCASE)

public class CrearPersona {
}
