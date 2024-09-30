package za.co.varsitycollege.st10092141.cashflow_v1
//ST10092141 - Kgothatso Theko
import retrofit2.Response
import retrofit2.http.*

//an interface that describes the API endpoints adapted from Medium
//https://medium.com/@pritam.karmahapatra/retrofit-in-android-with-kotlin-9af9f66a54a8#:~:text=Jan%2024,%202024.%2033.%20Retrofit%20is%20a%20popular%20HTTP%20client
//Pritam Kar Mahapatra
//https://medium.com/@pritam.karmahapatra?source=post_page-----9af9f66a54a8--------------------------------
interface ApiService {

    @POST("add-items")
    suspend fun addItem(@Body item: Item): Response<Item>

    @GET("get-items")
    suspend fun getItems(): Response<List<Item>>

    @GET("find-items/{id}")
    suspend fun getItemById(@Path("id") id: String): Response<Item>

    @PUT("update-items/{name}")
    suspend fun updateItem(
        @Path("name") name: String,
        @Body item: Item
    ): Response<Item>

    @DELETE("delete-items/{name}")
    suspend fun deleteItem(@Path("name") name: String): Response<Void>
}