package com.example.booksearchcompany

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.booksearchcompany.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initLoginButton()
        initJoinButton()
    }

    private fun initLoginButton() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "로그인에 실패했습니다. 이메일 & 비밀번호 확인해주세요",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun initJoinButton() {
        binding.signUpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입에 성공했습니다. 로그인 버튼을 눌러 로그인을 시도해주세요 ", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "이미 가입한 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}










































