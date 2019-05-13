package de.n26.n26blockchain.base.network

import de.n26.n26blockchain.base.network.request.BaseErrorModel
import de.n26.n26blockchain.base.network.request.BaseRequest

abstract class BaseRequestManager {
    abstract fun start(request: BaseRequest<out Any, BaseErrorModel>)
}