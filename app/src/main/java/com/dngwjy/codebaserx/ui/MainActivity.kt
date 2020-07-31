package com.dngwjy.codebaserx.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dngwjy.codebaserx.R
import com.dngwjy.codebaserx.base.LiveDataState
import com.dngwjy.codebaserx.base.OnError
import com.dngwjy.codebaserx.base.OnLoading
import com.dngwjy.codebaserx.base.ShowResult
import com.dngwjy.codebaserx.util.logD
import com.dngwjy.codebaserx.util.logE
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity(), Observer<LiveDataState> {
    private val mainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.liveDataState.observe(this, this)
        setContentView(R.layout.activity_main)
        mainViewModel.getTodo(Random.nextInt(1, 10))
    }

    override fun onChanged(t: LiveDataState?) {
        when (t) {
            is OnLoading -> {
                //do view on loading
                logE("load ${t.state.toString()}")
            }
            is OnError -> {
                //do view on error
                logE("load ${t.msg}")
            }
            is ShowResult -> {
                //do show result
                tv_data1.text=t.data.title
                logE("data : ${t.data.title}")
            }
        }
    }
}