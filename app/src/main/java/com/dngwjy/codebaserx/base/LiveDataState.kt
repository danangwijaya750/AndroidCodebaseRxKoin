package com.dngwjy.codebaserx.base

import com.dngwjy.codebaserx.data.model.Todo

sealed class LiveDataState
data class ShowResult(val data: Todo):LiveDataState()
data class OnError(val msg:String):LiveDataState()
data class OnLoading(val state: Boolean):LiveDataState()