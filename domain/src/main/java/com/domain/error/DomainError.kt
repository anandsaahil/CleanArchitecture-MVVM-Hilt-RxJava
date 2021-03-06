package com.domain.error

sealed class DomainError : Throwable() {
    class NoInternetException(override val message: String) : DomainError()
}
