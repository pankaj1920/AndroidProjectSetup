package com.applaunch.appbase.network_base


import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.listner_base.networkConnected
import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.BaseConstants
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.utils_base.checkApiCode
import com.applaunch.appbase.utils_base.convertErrorBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketException

abstract class NetworkBoundRepository<RESULT>(
    var iRepositoryListener: BaseRepoListener?,
    private val isShowProgress: Boolean = false,
    // 1- show/hide shime, 2 - type of shimmer
    private val showShimmer: Pair<Boolean, String> = Pair(false, BaseConstants.SHIMMER.NONE),
) {
    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State

        if (iRepositoryListener?.isNetworkConnected()!!) {

            if (isShowProgress) {
                iRepositoryListener?.showLoader()
            }
            if (showShimmer.first) {
                iRepositoryListener?.shimmer(true, showShimmer.second)
            }

            // Emit Database content first
            // Fetch latest posts from remote
            val apiResponse: Response<RESULT>

            // Parse body
            val remoteData: RESULT
            val baseResponse: BaseResponse?

            withContext(Dispatchers.IO) {
                apiResponse = fetchData()
            }

            // Save posts into the persistence storage
            if (apiResponse.isSuccessful && checkApiCode(apiResponse.code())) {
                remoteData = apiResponse.body()!!
                Print.log("Api Success = > ${remoteData}")
                emit(State.success(remoteData))
            } else {
                val errorResponse = convertErrorBody(apiResponse.errorBody())
                Print.log("Api Error  = > ${errorResponse?.message}")
                iRepositoryListener!!.showErrorMessage(errorResponse?.message.toString())
                emit(State.error(errorResponse?.message.toString()))
            }

            iRepositoryListener?.hideLoader()
            iRepositoryListener?.shimmer(false, showShimmer.second)

        }
    }.catch { e ->
        // Exception occurred! Emit error
        if (e is SocketException) {
            Print.log("SocketException : ${e.message}")
            networkConnected?.invoke(false)
        }
        iRepositoryListener?.hideLoader()
        iRepositoryListener?.shimmer(false, showShimmer.second)
        Print.log("Exception => (NetworkBoundRepository) : ${e}")
        Print.log("Exception => (NetworkBoundRepository) Message : ${e.message}")
        e.printStackTrace()

    }

    protected abstract suspend fun fetchData(): Response<RESULT>
}

