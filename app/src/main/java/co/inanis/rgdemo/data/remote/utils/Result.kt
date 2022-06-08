package co.inanis.rgdemo.data.remote.utils

sealed class Either<out L, out R> {
    data class Failure<out L>(val error: L) : Either<L, Nothing>()
    data class Success<out R>(val data: R) : Either<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isFailure get() = this is Failure<L>
    val success get() = (this as Success).data
    val failure get() = (this as? Failure)?.error

    fun either(fnL: (L) -> Unit, fnR: (R) -> Unit) = when (this) {
        is Failure -> fnL(error)
        is Success -> fnR(data)
    }

}
