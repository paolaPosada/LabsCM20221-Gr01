package co.edu.udea.compumovil.gr01_20221.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
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

        // Old
        /*
             read_json()

             var autoCompleteViewCity = findViewById<AutoCompleteTextView>(R.id.autoComplete_Ciudad)
             var adapterCity: ArrayAdapter<String> = ArrayAdapter<String>(this,
                 android.R.layout.simple_dropdown_item_1line, citiesArray)
            print(citiesArray)

             autoCompleteViewCity.setAdapter(adapterCity)
        */

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
    }

    fun configureCities() {
        var autoCompleteViewCity = findViewById<AutoCompleteTextView>(R.id.autoComplete_Ciudad)
        var adapterCity: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, citiesArray)

        autoCompleteViewCity.setAdapter(adapterCity)
    }


    fun read_json(){

        var json: String? = null

        try{
            val inputStream: InputStream = assets.open("colombianCities.json")
            json = inputStream.bufferedReader().use{it.readText()}

            var jsonArray = JSONArray(json)

            for (i in 0..jsonArray.length() - 1){
                var jsonObject = jsonArray.getJSONObject(i)
                citiesArray.add(jsonObject.getString("cityName"))
            }

        } catch (e: IOException){
            print(e)
        }

    }
}