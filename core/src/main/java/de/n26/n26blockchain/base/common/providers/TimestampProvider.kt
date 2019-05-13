package de.n26.n26blockchain.base.common.providers

import javax.inject.Inject

/**
 * Class to be able to test timestamp related features. Inject this instead of using System.currentTimeMillis()
 */
class TimestampProvider @Inject
internal constructor() {

    fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}
