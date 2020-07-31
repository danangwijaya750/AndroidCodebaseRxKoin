package com.dngwjy.codebaserx.di

import com.dngwjy.codebaserx.data.repository.TodoRepository
import org.koin.dsl.module.module

val repositoryModule = module{
    single {
        TodoRepository(get())
    }
}