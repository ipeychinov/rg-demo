package co.inanis.rgdemo.data.remote

data class ApiErrorResponse(
    val error: ApiError
)

data class ApiError(
    val code: Int,
    val message: String,
)
