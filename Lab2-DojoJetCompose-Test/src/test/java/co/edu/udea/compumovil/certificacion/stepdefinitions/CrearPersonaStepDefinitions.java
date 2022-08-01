package co.edu.udea.compumovil.certificacion.stepdefinitions;

import co.edu.udea.compumovil.certificacion.questions.CamposDiligenciados;
import co.edu.udea.compumovil.certificacion.questions.VisualizarLista;
import co.edu.udea.compumovil.certificacion.tasks.ConfirmarFormulario;
import co.edu.udea.compumovil.certificacion.tasks.DiligenciarFormulario;
import co.edu.udea.compumovil.certificacion.tasks.Inicializar;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import static co.edu.udea.compumovil.certificacion.utils.Constantes.*;

public class CrearPersonaStepDefinitions {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("^El usuario inicia el app y le da click en el boton nuevo$")
    public void elUsuarioIniciaElAppYLeDaClickEnElBotonNuevo() {
        OnStage.theActorInTheSpotlight().attemptsTo(Inicializar.crearPersona());
    }

    @When("^El usuario ingresa el (.*), el (.*) y la (.*) en el formulario$")
    public void elUsuarioIngresaNombreApellidoYCiudadEnElFormulario(String nombre, String apellido, String ciudad) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DiligenciarFormulario.ingresarDatos(nombre, apellido, ciudad )
        );
    }

    @When("^Confirma con el boton anadir$")
    public void confirmaConElBotonAnadir() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConfirmarFormulario.enviarDatos()
        );
    }

    @Then("^El usuario visualiza la nueva persona en la lista$")
    public void elUsuarioVisualizaLaNuevaPersonaEnLaLista() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(CamposDiligenciados.validarCampo(CAMPO_NOMBRES_PERSONA), Matchers.not(Matchers.isEmptyOrNullString())),
                GivenWhenThen.seeThat(CamposDiligenciados.validarCampo(CAMPO_APELLIDOS_PERSONA), Matchers.not(Matchers.isEmptyOrNullString())),
                GivenWhenThen.seeThat(CamposDiligenciados.validarCampo(CAMPO_CIUDAD_PERSONA), Matchers.not(Matchers.isEmptyOrNullString()))
        );
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(VisualizarLista.validarPersona())
        );
    }

}
