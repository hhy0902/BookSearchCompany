package com.example.booksearchcompany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.booksearchcompany.databinding.ActivityBookDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BookDetailActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private val auth : FirebaseAuth = Firebase.auth

    private val binding by lazy {
        ActivityBookDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val imageCover = intent.getStringExtra("imageCover")
        val author = intent.getStringExtra("author")
        val date = intent.getStringExtra("date")
        val isbn13 = intent.getStringExtra("isbn13")
        val publisher = intent.getStringExtra("publisher")
        val customerReviewRank = intent.getIntExtra("customerReviewRank", 0)
        val priceStandard = intent.getIntExtra("priceStandard", 0)
        val priceSales = intent.getIntExtra("priceSales", 0)
        val categoryName = intent.getStringExtra("categoryName")
        val description = intent.getStringExtra("description")
        val link = intent.getStringExtra("link")

        binding.titleTextView.text = title
        binding.authorTextview.text = author.toString()
        binding.publisherTextview.text = publisher
        binding.dateTextview.text = date
        binding.priceSalesTextView.text = priceSales.toString()
        binding.priceStandardTextview.text = priceStandard.toString()
        when(customerReviewRank) {
            10 -> binding.customerReviewRankTextView.text = "⭐⭐⭐⭐⭐"
            9 -> binding.customerReviewRankTextView.text = "⭐⭐⭐⭐"
            8 -> binding.customerReviewRankTextView.text = "⭐⭐⭐⭐"
            7 -> binding.customerReviewRankTextView.text = "⭐⭐⭐"
            6 -> binding.customerReviewRankTextView.text = "⭐⭐⭐"
            5 -> binding.customerReviewRankTextView.text = "⭐⭐"
            4 -> binding.customerReviewRankTextView.text = "⭐⭐"
            3 -> binding.customerReviewRankTextView.text = "⭐"
            2 -> binding.customerReviewRankTextView.text = "⭐"
            1 -> binding.customerReviewRankTextView.text = "👎"
            else -> binding.customerReviewRankTextView.text = "🧐"
        }

        Glide
            .with(binding.coverImage.context)
            .load(imageCover)
            .fitCenter()
            .into(binding.coverImage)

        binding.checkbox.setOnCheckedChangeListener { buttonCheck, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "checked", Toast.LENGTH_SHORT).show()
                val bookInfo = hashMapOf(
                    "title" to title,
                    "author" to author,
                    "date" to date,
                    "cover" to imageCover,
                    "publisher" to publisher,
                    "customerReviewRank" to customerReviewRank,
                    "priceStandard" to priceStandard,
                    "priceSales" to priceSales,
                    "categoryName" to categoryName
                )

//                add는 중복해서 만들어져서 안됨
//                db.collection("bookMark").add(bookInfo).addOnSuccessListener {
//                    Log.d("firestore user", "${it.id}")
//                }
                db.collection("${auth.currentUser?.uid}bookMark").document(
                    "$author$title").set(bookInfo).addOnSuccessListener {

                }

//                db.collection("bookMark").document("$title").get().addOnSuccessListener { document ->
//                    Log.d("document", "${document.get("title")}")
//                    Log.d("document", "${document.get("author")}")
//                    Log.d("document", "${document.get("date")}")
//                }
//
//                db.collection("bookMark").get().addOnSuccessListener {
//                    for(document in it) {
//                        Log.d("bookMark", "${document.data.getValue("title")}")
//                    }
//
//                }

            } else {
                Toast.makeText(this, "unChecked", Toast.LENGTH_SHORT).show()
            }
        }

    }
}






































