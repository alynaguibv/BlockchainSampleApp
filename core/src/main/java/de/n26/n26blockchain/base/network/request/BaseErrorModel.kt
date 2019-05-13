package de.n26.n26blockchain.base.network.request

class BaseErrorModel {
    var errorCode = -1
    var errorMessage = ""
    var throwable: Throwable = Throwable()

    constructor() {}

    constructor(errorCode: Int, errorMessage: String) {
        this.errorCode = errorCode
        this.errorMessage = errorMessage
    }

    constructor(throwable: Throwable) {
        this.throwable = throwable
    }
}
