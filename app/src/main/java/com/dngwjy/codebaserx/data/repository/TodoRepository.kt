package com.dngwjy.codebaserx.data.repository

import com.dngwjy.codebaserx.data.model.Todo
import com.dngwjy.codebaserx.data.service.ApiService
import io.reactivex.Observable

class TodoRepository (private val service: ApiService){
    fun getTodo(id:Int):Observable<Todo> = service.getTodo(id)
}