package com.example.booksearchcompany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.booksearchcompany.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBookDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val imageCover = intent.getStringExtra("imageCover")

        binding.testTextView.text = title
        Glide
            .with(binding.coverImage.context)
            .load(imageCover)
            .override(500, 500)
            .fitCenter()
            .into(binding.coverImage)

    }
}






































