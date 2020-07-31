package com.dngwjy.codebaserx.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dngwjy.codebaserx.util.logE
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.io.IOException

open class BaseViewModel:ViewModel(){
    val liveDataState= MutableLiveData<LiveDataState>()

    protected val disposeable = CompositeDisposable()

    fun errorHandling(t:Throwable){
        when(t){
            is IOException ->{
                logE("Network Error")
                liveDataState.postValue(OnError("Network Error"))
            }
            is HttpException ->{
                logE("error ${t.code()} : ${t.message()} ")
            }
            else->{
                logE(t.localizedMessage)
            }
        }
    }

    protected fun dispose(){
        if(!disposeable.isDisposed) disposeable.dispose()
    }
}