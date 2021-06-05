package com.buchi.core.utils

data class ResultState<T>(
    var message: Event<String>? = null,
    var error: Event<Throwable>? = null,
    var loading: Boolean = false,
    var data: Event<T>? = null
) {
    companion object {
        fun <T> error(
            error: Throwable,
            message: String? = null
        ): ResultState<T> {
            return ResultState(
                error = Event(error),
                loading = false,
                data = null,
                message = Event.messageEvent(message)
            )
        }

        fun <T> loading(
            isLoading: Boolean,
            message: String? = null
        ): ResultState<T> {
            return ResultState(
                error = null,
                loading = isLoading,
                data = null,
                message = Event.messageEvent(message)
            )
        }

        fun <T> data(
            data: T? = null,
            message: String? = null
        ): ResultState<T> {
            return ResultState(
                error = null,
                loading = false,
                data = Event.dataEvent(data),
                message = Event.messageEvent(message)
            )
        }
    }

    override fun toString(): String {
        return "com.ajocard.core.utils.DataState(loading=$loading,data=$data)"
    }
}