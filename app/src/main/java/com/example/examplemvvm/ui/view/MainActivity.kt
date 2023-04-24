package com.example.examplemvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.examplemvvm.databinding.ActivityMainBinding
import com.example.examplemvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        //PATRON OBSERVE A TRAVES DE LIVE DATA
        /* Lo que este dentro de las llaves esta conectado a LiveData,
        por ejemplo una nueva cita se va a ejecutar el seteo la cita en la activity*/
        quoteViewModel.quoteModel.observe(this, Observer {
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        })

        quoteViewModel.isLoading.observe(this,Observer{
            binding.progress.isVisible = it
        })


        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }
    /*
    quoteViewModel.quoteModel.observe(this, Observer {currentQuote -> //El nuevo valor se va a llamar asi
        binding.tvAuthor.text = currentQuote.author
    })*/
    }
}