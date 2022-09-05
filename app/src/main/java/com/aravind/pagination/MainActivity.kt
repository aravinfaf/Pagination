package com.aravind.pagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerview()
        intializeViewmodel()
    }

    fun initRecyclerview() {
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            recyclerview.addItemDecoration(decoration)

            adapter = PagingAdapter()
            recyclerview.adapter = adapter
    }

    fun intializeViewmodel(){
        val viewmodel = ViewModelProvider(this).get(MainViewmodel::class.java)
        lifecycleScope.launchWhenCreated {
            viewmodel.getListData().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}