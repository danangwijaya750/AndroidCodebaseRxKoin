package com.dngwjy.codebaserx.di

import com.dngwjy.codebaserx.ui.MainViewModel
import org.koin.dsl.module.module

val viewModelModule=module{
    single{
        MainViewModel(get())
    }
}