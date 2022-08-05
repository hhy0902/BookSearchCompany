package com.example.booksearchcompany

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.booksearchcompany.bestseller.BestSellerFragment
import com.example.booksearchcompany.bookmark.BookMarkFragment
import com.example.booksearchcompany.databinding.ActivityMainBinding
import com.example.booksearchcompany.mypage.MypageFragment
import com.example.booksearchcompany.newbook.NewBookFragment
import com.example.booksearchcompany.search.SearchFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val auth : FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bestSellerFragment = BestSellerFragment()
        val mypageFragment = MypageFragment()
        val bookMarkFragment = BookMarkFragment()
        val newBookFragment = NewBookFragment()
        val searchFragment = SearchFragment()

        replaceFragment(bestSellerFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.myPage -> {
                    replaceFragment(mypageFragment)
                }
                R.id.bestSeller -> replaceFragment(bestSellerFragment)
                R.id.bookMark -> replaceFragment(bookMarkFragment)
                R.id.newBook -> replaceFragment(newBookFragment)
                R.id.searchBook -> replaceFragment(searchFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this,"login 하세요!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}







































