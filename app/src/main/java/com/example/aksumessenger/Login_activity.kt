package com.example.aksumessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login_activity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.edit_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, Signup_activity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
           val email =edtEmail.text.toString()
           val password = edtPassword.text.toString()
            
            login(email, password);
        }
    }

    private fun login(email: String, password: String) {
        //logic of creating User
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in User's information
                    val intent = Intent(this@Login_activity, MainActivity::class.java )
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the User.
                    Toast.makeText(this, "there is no such account", Toast.LENGTH_SHORT).show()

                }
            }

    }
}