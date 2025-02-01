package ru.kpfu.minn.core.common.utils

import android.content.Context
import javax.inject.Inject

class StringResProvider @Inject constructor(
    private val ctx: Context
) {
    fun getString(resId: Int): String = ctx.getString(resId)
}