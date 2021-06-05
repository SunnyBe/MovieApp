package com.buchi.fullentry.utilities

import android.view.View
import java.math.RoundingMode
import java.text.DecimalFormat

val String.appendCurrency get() = "${Constants.CURRENCY} $this"
val Long.appendCurrency get() = "${Constants.CURRENCY} $this"
val Double.appendCurrency get() = "${Constants.CURRENCY} $this"

val Double.formatAmount get() = kotlin.run {
    val df = DecimalFormat("#,###.00")
    df.roundingMode = RoundingMode.DOWN
    df.format(this).appendCurrency
}

val Double.to2dp get() = run {
    String.format("%.2f", this)
}

val Double.to1dp get() = run {
    String.format("%.1f", this)
}

val String.inBrace get() = "($this)"

fun View.viewvisibility(isVisible: Boolean) {
    if (isVisible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}