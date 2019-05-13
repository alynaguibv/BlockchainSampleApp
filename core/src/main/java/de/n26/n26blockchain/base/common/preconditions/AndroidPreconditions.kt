package de.n26.n26blockchain.base.common.preconditions

import android.os.Looper

import javax.inject.Inject

/**
 * Created by Lucia on 11/07/2017.
 */
class AndroidPreconditions @Inject
internal constructor() {

    /**
     * Returns whether the current thread is the Android main thread
     * @return true if the current thread is the main thread, otherwise; false.
     */
    val isMainThread: Boolean
        get() = Looper.getMainLooper().thread == Thread.currentThread()

    /**
     * Asserts that the current thread is a worker thread.
     */
    fun assertWorkerThread() {
        if (isMainThread) {
            throw IllegalStateException(
                    "This task must be run on a worker thread and not on the Main thread.")
        }
    }

    /**
     * Asserts that the current thread is the Main Thread.
     */
    fun assertUiThread() {
        if (!isMainThread) {
            throw IllegalStateException(
                    "This task must be run on the Main thread and not on a worker thread.")
        }
    }
}
