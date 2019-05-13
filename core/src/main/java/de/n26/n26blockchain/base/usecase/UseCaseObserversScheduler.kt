package de.n26.n26blockchain.base.usecase

import android.os.Handler
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UseCaseObserversScheduler : UseCaseScheduler {
    override fun <Q : UseCase.RequestValues, V : UseCase.ResponseValue> execute(useCase: UseCase<Q, V>) {
        val observer = object : Observer<Any> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: Any) {
            }

            override fun onError(e: Throwable) {
                Log.e("", "", e)
            }

            override fun onComplete() {
            }

        }
        val observable = Observable.defer {
             ObservableSource<String> {
                useCase.run()
                 Observable.just(String())
            }
        }
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }

    private val mHandler = Handler()


    init {

    }

    override fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mHandler.post { useCaseCallback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValue> onError(
        useCaseCallback: UseCase.UseCaseCallback<V>, t: Throwable
    ) {
        mHandler.post { useCaseCallback.onError(t) }
    }

}