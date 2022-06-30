package co.edu.udea.compumovil.gr01_20221.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        findViewById<EditText>(R.id.select_fecha).setOnClickListener { showDatePickerDialog() }

        findViewById<Button>(R.id.button_sgte).setOnClickListener {
            var etNombres = findViewById<EditText>(R.id.editText_Nombres)
            var etApellidos = findViewById<EditText>(R.id.editText_Apellidos)
            var etFechaNacimiento = findViewById<EditText>(R.id.select_fecha)
            if( etNombres.text.isEmpty()  || etApellidos.text.isEmpty() || etFechaNacimiento.text.isEmpty()){
                showError()
            } else {
                val intent = Intent(this@PersonalDataActivity, ContactDataActivity::class.java)
                startActivity(intent)
                Log.d("Nombres", etNombres.text.toString())
                Log.d("Apellidos", etApellidos.text.toString())
                Log.d("Fecha de nacimiento", etFechaNacimiento.text.toString())
            }
        };

    }

    private fun showError() {
        Toast.makeText(this, "¡Debe llenar todos los campos obligatorios!", Toast.LENGTH_LONG).show()
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        findViewById<EditText>(R.id.select_fecha).setText("$day-$month-$year")
    }



}