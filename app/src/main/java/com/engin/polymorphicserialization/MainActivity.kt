package com.engin.polymorphicserialization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.engin.polymorphicserialization.databinding.ActivityMainBinding
import com.engin.polymorphicserialization.ui.MainRecyclerViewAdapter
import com.engin.polymorphicserialization.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainAdapter : MainRecyclerViewAdapter

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindUI()
        observeFlow()
        viewModel.getSections()
    }

    private fun bindUI() {
        mainAdapter = MainRecyclerViewAdapter()
        with(binding.mainRecyclerView){
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeFlow(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                launch {
                    viewModel.error.collectLatest {
                        Toast.makeText(this@MainActivity,it,Toast.LENGTH_SHORT).show()
                    }
                }
                launch {
                    viewModel.isLoading.collectLatest {
                        binding.progressCircular.visibility = if (it) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.sectionList.collectLatest {
                        mainAdapter.updateList(it)
                    }
                }
            }
        }
    }
}