package de.n26.n26androidsamples.base.common.utils

import java.text.NumberFormat
import java.util.Locale

import javax.inject.Inject

class CurrencyUtils @Inject
internal constructor() {

    fun formatAmount(amount: Double): String {
        return NumberFormat.getCurrencyInstance(Locale.GERMANY).format(amount)
    }
}
