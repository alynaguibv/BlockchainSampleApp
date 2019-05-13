package app.sample.blockchainsampleapp.blockchainstats.data.source.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class Localstore() {


    companion object {

        private var INSTANCE: Localstore? = null

        private val lock = Any()

        @JvmStatic
        fun getInstance(): Localstore {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Localstore()
                }
                return INSTANCE!!
            }
        }

        fun clearInstance() {
            INSTANCE = null
        }
    }

    fun getPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}