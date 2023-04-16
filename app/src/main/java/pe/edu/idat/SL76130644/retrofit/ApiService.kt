package pe.edu.idat.SL76130644.retrofit

import retrofit2.http.GET
import pe.edu.idat.SL76130644.model.Character


interface ApiService {
    @GET("/api/characters")
    suspend fun getCharacters(): List<Character>
}