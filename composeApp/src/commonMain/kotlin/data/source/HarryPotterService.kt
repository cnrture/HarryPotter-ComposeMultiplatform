package data.source

import data.model.BaseResponse
import data.model.Character
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class HarryPotterService(private val client: HttpClient) {
    companion object {
        private const val API_URL = "https://api.canerture.com/harrypotterapp/"
    }

    suspend fun getCharacters(): BaseResponse<List<Character>> {
        return client.get(API_URL.plus("characters")).body()
    }

    suspend fun getCharacterDetail(id: Int): BaseResponse<Character> {
        return client.get(API_URL.plus("characters")){
            parameter("id", id)
        }.body()
    }
}