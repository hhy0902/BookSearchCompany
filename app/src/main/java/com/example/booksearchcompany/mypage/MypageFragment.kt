package com.example.booksearchcompany.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.booksearchcompany.LoginActivity
import com.example.booksearchcompany.R
import com.example.booksearchcompany.databinding.FragmentMypageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MypageFragment : Fragment(R.layout.fragment_mypage){

    private val auth : FirebaseAuth = Firebase.auth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentMypageBinding = FragmentMypageBinding.bind(view)

        if (auth.currentUser != null) {
            fragmentMypageBinding.emailValue.text = auth.currentUser!!.email
            fragmentMypageBinding.uidValue.text = auth.currentUser!!.uid
        }

        fragmentMypageBinding.logoutButton.setOnClickListener {
            Log.d("logoutButton", "logoutButton")
            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            // activity에서 finish 하는거 랑 동일
            activity?.finish()
        }


    }


}












































