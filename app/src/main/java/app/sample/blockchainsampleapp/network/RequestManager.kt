package app.sample.blockchainsampleapp.network

import dagger.Module
import de.n26.n26blockchain.base.network.BaseRequestManager
import de.n26.n26blockchain.base.network.request.BaseErrorModel
import de.n26.n26blockchain.base.network.request.BaseRequest
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import javax.inject.Inject

@Module
class RequestManager private constructor() : BaseRequestManager() {

    private var client: OkHttpClient? = null
    private var httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient().newBuilder().addNetworkInterceptor(httpLoggingInterceptor).build()
    }

    companion object {
        private var INSTANCE: RequestManager? = null

        private val lock = Any()

        fun getInstance(): RequestManager {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = RequestManager()
                }
                return INSTANCE!!
            }
        }
    }

    override fun start(request: BaseRequest<out Any, BaseErrorModel>) {
        var errorModel: BaseErrorModel? = null
        try {
            val responseBody = get(request.baseURL + request.path)
            request.parseResponse(responseBody)
            if (request.response == null) {
                errorModel = BaseErrorModel(500, "No Response Body")
            }
            if (errorModel != null) {
                request.baseErrorModel = errorModel
            }
        } catch (e: Exception) {
            errorModel = BaseErrorModel(e)
            request.baseErrorModel = errorModel
            e.printStackTrace()
        }

    }

    @Throws(IOException::class)
    private fun get(url: String): String {
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        client!!.newCall(request).execute().use { response -> return response.body()!!.string() }
    }

}