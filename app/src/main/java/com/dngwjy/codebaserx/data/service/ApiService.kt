package com.dngwjy.codebaserx.data.service

import com.dngwjy.codebaserx.data.model.Todo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/todos/{id}")
    fun getTodo(@Path("id")id:Int):Observable<Todo>
}