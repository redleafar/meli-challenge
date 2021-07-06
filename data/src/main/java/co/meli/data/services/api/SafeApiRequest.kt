package co.meli.data.services.api

import co.meli.domain.models.ResultWrapper
import com.google.gson.JsonParser
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {
    suspend fun<T: Any> apiRequest(apiCall: suspend() -> T) : ResultWrapper<T> {
        return try {
            val response = apiCall.invoke()
            ResultWrapper.Success(response)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): String? {
        return try {
            throwable.response()?.errorBody()?.string()?.let {
                val jsonParser = JsonParser().parse(it)
                jsonParser.asJsonObject.get("message").asString
            }
        } catch (exception: Exception) {
            null
        }
    }
}