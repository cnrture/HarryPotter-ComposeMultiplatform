package data.repository

import data.model.Character
import data.source.HarryPotterService

class CharactersRepository(private val harryPotterService: HarryPotterService) {

    suspend fun getCharacters(): Result<List<Character>> {
        return try {
            val response = harryPotterService.getCharacters()
            if (response.status == 200) {
                Result.success(response.data.orEmpty())
            } else {
                Result.failure(Exception(response.message.orEmpty()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getCharacterDetail(id: Int) : Result<Character> {
        return try {
            val response = harryPotterService.getCharacterDetail(id)
            if (response.status == 200 && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message.orEmpty()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}