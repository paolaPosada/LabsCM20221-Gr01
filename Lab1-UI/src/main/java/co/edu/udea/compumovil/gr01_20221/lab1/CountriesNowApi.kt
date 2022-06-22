package co.edu.udea.compumovil.gr01_20221.lab1

import co.edu.udea.compumovil.gr01_20221.lab1.model.CitiesResponse
import co.edu.udea.compumovil.gr01_20221.lab1.model.CountryBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Body

interface CountriesNowApi {

    // https://countriesnow.space/api/v0.1/countries/
    @POST("cities")
    fun getCitiesByCountry(
        @Body countryBody: CountryBody
    ): Call<CitiesResponse>
}