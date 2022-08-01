package co.edu.udea.compumovil.certificacion.userinterfases;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class FormularioPersona {
    public static final Target NOMBRES_TEXTO = Target.the("Nombre Texto")
            .located(By.xpath("//android.widget.EditText//*[matches(@text,'^Name')]/.."));

    public static final Target APELLIDOS_TEXTO = Target.the("Apellidos Texto")
            .located(By.xpath("//android.widget.EditText//*[matches(@text,'^LastName')]/.."));

    public static final Target CIUDAD_TEXTO = Target.the("Ciudad Texto")
            .located(By.xpath("//android.widget.EditText//*[matches(@text,'^City')]/.."));

    public static final Target ANADIR_PERSONA_BOTON = Target.the("Anadir Persona")
            .located(By.className("android.widget.Button"));
}
