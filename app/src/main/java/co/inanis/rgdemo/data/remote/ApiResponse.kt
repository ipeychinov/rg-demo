package co.inanis.rgdemo.data.remote

data class ApiResponse<T>(
    val contents: T,
)