package data.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int?,
    val name: String?,
    val imageUrl: String?,
    val description: String?,
    val house: String?,
)