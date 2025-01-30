package ru.kpfu.minn.core.common.utils

import kotlin.coroutines.cancellation.CancellationException

inline fun <R> runSuspendCatching(
    exceptionHandlerDelegate: ExceptionHandlerDelegate,
    block: () -> R,
): Result<R> {
    return try {
        Result.success(block())
    } catch (c: CancellationException) {
        throw c
    } catch (e: Throwable) {
        Result.failure(exceptionHandlerDelegate.handleException(e))
    }
}