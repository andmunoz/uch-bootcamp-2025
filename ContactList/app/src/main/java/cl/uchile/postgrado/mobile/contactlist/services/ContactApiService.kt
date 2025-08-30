package cl.uchile.postgrado.mobile.contactlist.services

import cl.uchile.postgrado.mobile.contactlist.room.Contact
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ContactApiService {
    object RetrofitInstance {
        private const val BASE_URL = "https://ejemplo-firebase-657d0-default-rtdb.firebaseio.com/"

        val api: ContactApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ContactApiService::class.java)
        }
    }

    @GET("contacts.json")
    suspend fun getContacts(): List<Contact>

    @GET("contacts/{id}.json")
    suspend fun getContactById(@Path("id") id: Int): Contact?

    @POST("contacts.json")
    suspend fun addContact(@Body contact: Contact): Contact

    @PUT("contacts/{id}.json")
    suspend fun updateContact(@Path("id") id: Int, @Body contact: Contact): Contact

    @DELETE("contacts/{id}.json")
    suspend fun deleteContact(@Path("id") id: Int)
}