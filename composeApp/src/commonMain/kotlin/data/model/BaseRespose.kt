package data.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse<T> (
    val data: T? = null,
    val status: Int? = null,
    val message: String? = null,
)