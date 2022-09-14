package com.example.aksumessenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup_activity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        edtName = findViewById(R.id.name)
        edtPassword = findViewById(R.id.edit_password)
        btnSignUp = findViewById(R.id.btn_signup)
        edtEmail = findViewById(R.id.email)

        btnSignUp.setOnClickListener {
            val email =edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val name = edtName.text.toString()

            signUp(name, email,password);

        }
    }
    private fun signUp(name: String, email:String, password: String){
        //logic of creating User
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in User's information
                 val intent = Intent(this@Signup_activity, MainActivity::class.java )
                 startActivity(intent)
                    finish()

                    //adding User to the database
                    addUsertoDataBase(name, email, mAuth.currentUser?.uid!!)


                } else {
                    // If sign in fails, display a message to the User.
                    Toast.makeText(this, "some error occured", Toast.LENGTH_SHORT).show()

                }
            }


    }

    private fun addUsertoDataBase(name: String, email: String, uid: String) {
    mDbRef = FirebaseDatabase.getInstance().getReference()
    mDbRef.child("User").child(uid).setValue(User(name, email, uid))

    }


}