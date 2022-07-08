package co.edu.udea.compumovil.gr01_20221.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        findViewById<EditText>(R.id.select_fecha).setOnClickListener { showDatePickerDialog() }

        findViewById<Button>(R.id.button_sgte).setOnClickListener {
            var etNombres = findViewById<EditText>(R.id.editText_Nombres)
            var etApellidos = findViewById<EditText>(R.id.editText_Apellidos)
            var etFechaNacimiento = findViewById<EditText>(R.id.select_fecha)
            var rbHombre = findViewById<RadioButton>(R.id.radio_hombre)
            var rbMujer = findViewById<RadioButton>(R.id.radio_mujer)
            var checkedHombre = rbHombre.isChecked
            var checkedMujer = rbMujer.isChecked
            var spGradoEscolaridad = findViewById<Spinner>(R.id.spinner_grado)
            var gradoEscolaridad = spGradoEscolaridad.selectedItem.toString()
            var genero: String = ""
            if (checkedHombre) {
                genero = "Masculino"
            } else if (checkedMujer) {
                genero = "Femenino"
            } else {
                genero = "No aplica"
            }

            if(gradoEscolaridad ==  "Grado de escolaridad") {
                gradoEscolaridad = "Sin selección"
            }

            if( etNombres.text.isEmpty()  || etApellidos.text.isEmpty() || etFechaNacimiento.text.isEmpty()){
                showError()
            } else {
                val intent = Intent(this@PersonalDataActivity, ContactDataActivity::class.java)
                var nombreCompleto: String = etNombres.text.toString() + " " + etApellidos.text.toString()
                intent.putExtra("fullName", nombreCompleto)
                intent.putExtra("gender", genero)
                intent.putExtra("birthDate", etFechaNacimiento.text.toString())
                intent.putExtra("schoolGrade",gradoEscolaridad)
                startActivity(intent)
            }
        };

    }

    private fun showError() {
        Toast.makeText(this, "❌  Error", Toast.LENGTH_LONG).show()
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        findViewById<EditText>(R.id.select_fecha).setText("$day-$month-$year")
    }



}