Feature: Crear persona

  Scenario Outline: Crear una persona exitoso
    Given El usuario inicia el app y le da click en el boton nuevo
    When El usuario ingresa el <nombre>, el <apellido> y la <ciudad> en el formulario
    And  Confirma con el boton anadir
    Then El usuario visualiza la nueva persona en la lista

    Examples:
      |nombre|apellido|ciudad|
      |Paola|Posada|Amaga|
      |Juan  |        |Concepcion|
      |      |Rios   |3       |

