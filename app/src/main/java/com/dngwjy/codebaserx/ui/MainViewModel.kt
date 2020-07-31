package com.dngwjy.codebaserx.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.dngwjy.codebaserx.base.BaseViewModel
import com.dngwjy.codebaserx.base.OnLoading
import com.dngwjy.codebaserx.base.ShowResult
import com.dngwjy.codebaserx.data.model.Todo
import com.dngwjy.codebaserx.data.repository.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: TodoRepository) :BaseViewModel(){

    private lateinit var todoData:Todo
    fun getTodo(id:Int){
        if(this::todoData.isInitialized){
            liveDataState.value = ShowResult(todoData)
            return
        }

        disposeable.addAll(
            repository.getTodo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{
                    liveDataState.value = OnLoading(true)
                }
                .doOnComplete {
                    liveDataState.value= OnLoading(false)
                }
                .subscribe(
                    {
                        todoData=it
                        liveDataState.value =ShowResult(todoData)
                    },this::errorHandling
                )
        )
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(){
        dispose()
    }


}