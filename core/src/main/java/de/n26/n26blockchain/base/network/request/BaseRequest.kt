package de.n26.n26blockchain.base.network.request

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

open class BaseRequest<M, E> {

    var response: M? = null
    var baseErrorModel: E? = null
    var path: String? = null
        protected set
    var baseURL: String? = null

    private val modelClass: Class<M>
        get() {
            var genericSuperClass = javaClass.genericSuperclass

            var parametrizedType: ParameterizedType? = null
            while (parametrizedType == null) {
                if (genericSuperClass is ParameterizedType) {
                    parametrizedType = genericSuperClass
                } else {
                    genericSuperClass = (genericSuperClass as Class<*>).genericSuperclass
                }
            }
            @Suppress("UNCHECKED_CAST")
            return parametrizedType.actualTypeArguments[0] as Class<M>
        }

    fun parseResponse(responseString: String): M? {
        val gson = Gson()
        try {
            @Suppress("UNCHECKED_CAST")
            response = gson.fromJson(responseString, modelClass) as M
            return response
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}
