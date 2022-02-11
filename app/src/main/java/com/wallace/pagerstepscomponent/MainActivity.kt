package com.wallace.pagerstepscomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wallace.pagerstepscomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pscPagination.setupAdapter(
            arrayListOf(
                AnyModel("Pagina 1", 1),
                AnyModel("Pagina 2", 2),
                AnyModel("Pagina 3", 3),
                AnyModel("Pagina 4", 4)
            )
        )

        binding.btnNextPage.setOnClickListener {
            binding.pscPagination.nextPage()
        }
    }
}