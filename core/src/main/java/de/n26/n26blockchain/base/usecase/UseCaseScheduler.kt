package de.n26.n26blockchain.base.usecase

interface UseCaseScheduler {

    fun <Q : UseCase.RequestValues, V : UseCase.ResponseValue> execute(useCase: UseCase<Q, V>)

    fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>
    )

    fun <V : UseCase.ResponseValue> onError(
        useCaseCallback: UseCase.UseCaseCallback<V>, t: Throwable
    )
}