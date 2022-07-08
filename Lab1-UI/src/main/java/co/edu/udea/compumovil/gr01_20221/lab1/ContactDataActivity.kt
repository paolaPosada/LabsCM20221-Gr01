package co.edu.udea.compumovil.gr01_20221.lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import co.edu.udea.compumovil.gr01_20221.lab1.model.CitiesResponse
import co.edu.udea.compumovil.gr01_20221.lab1.model.CountryBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream

class ContactDataActivity : AppCompatActivity() {

    var citiesArray = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        var autoCompleteViewPais = findViewById<AutoCompleteTextView>(R.id.autoComplete_Pais)
        var countriesArray: Array<String> = resources.getStringArray(R.array.paises_latinoamerica)

        var adapterCountry: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, countriesArray)

        autoCompleteViewPais.setAdapter(adapterCountry)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://countriesnow.space/api/v0.1/countries/")
            .build()

        val countriesNowApi = retrofitBuilder.create(CountriesNowApi::class.java)

        var countryPost = CountryBody("colombia")

        val call = countriesNowApi.getCitiesByCountry(countryPost)

        call.enqueue(object : Callback<CitiesResponse> {
            override fun onFailure(call: Call<CitiesResponse>, t: Throwable) {
                print(t)
            }

            override fun onResponse(
                call: Call<CitiesResponse>,
                response: Response<CitiesResponse>
            ) {
                citiesArray = response.body()!!.data
                configureCities()
            }
        })

        findViewById<Button>(R.id.button_sget_v2).setOnClickListener {

            var etTelefono = findViewById<EditText>(R.id.editText_Telefono)
            var etCorreo = findViewById<EditText>(R.id.editText_Correo)
            var etDireccion = findViewById<EditText>(R.id.editText_Direccion)
            var autoCompleteViewCity = findViewById<AutoCompleteTextView>(R.id.autoComplete_Ciudad)
            autoCompleteViewPais = findViewById<AutoCompleteTextView>(R.id.autoComplete_Pais)

            if( etTelefono.text.isEmpty()  || etCorreo.text.isEmpty() || autoCompleteViewPais.text.isEmpty()){
                showError()
            } else {
                Toast.makeText(this, "✔", Toast.LENGTH_LONG).show()
                var intent: Intent = getIntent()
                val nombreCompleto = intent.getStringExtra("fullName")
                val genero = intent.getStringExtra("gender")
                val fechaNacimiento = intent.getStringExtra("birthDate")
                val gradoEscolaridad = intent.getStringExtra("schoolGrade")
                var ciudad = "";
                ciudad = if (autoCompleteViewCity.text.isEmpty()){
                    "No aplica"
                } else {
                    autoCompleteViewCity.text.toString()
                }

                var direccion = "";
                direccion = if (etDireccion.text.isEmpty()){
                    "No aplica"
                } else {
                    etDireccion.text.toString()
                }


                Log.d("-----", "Información personal-----")
                Log.d("Nombre completo ", nombreCompleto.toString())
                Log.d("Género ", genero.toString())
                Log.d("Nació el ", fechaNacimiento.toString())
                Log.d("Grado de escolaridad ", gradoEscolaridad.toString())
                Log.d("-----", "Información de contacto-----")
                Log.d("Teléfono ", etTelefono.text.toString())
                Log.d("Dirección ", direccion)
                Log.d("Email ", etCorreo.text.toString())
                Log.d("País ", autoCompleteViewPais.text.toString())
                Log.d("Ciudad ", ciudad)
            }
        };
    }

    fun configureCities() {
        var autoCompleteViewCity = findViewById<AutoCompleteTextView>(R.id.autoComplete_Ciudad)
        var adapterCity: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, citiesArray)

        autoCompleteViewCity.setAdapter(adapterCity)
    }

    private fun showError() {
        Toast.makeText(this, "❌  Error", Toast.LENGTH_LONG).show()
    }
}