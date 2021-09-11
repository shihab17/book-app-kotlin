package com.example.bookappkotlin

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.bookappkotlin.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    private  lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnRegister.setOnClickListener {
            validateData()
        }
    }
    private var name =""
    private var email =""
    private var password =""
    private var confirmPassword =""

    private fun validateData() {
        name = binding.etName.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        confirmPassword = binding.etConfirmPassword.text.toString().trim()

        if (name.isEmpty()){
            Toast.makeText(this,"Enter your name...", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Enter your valid Email....", Toast.LENGTH_SHORT).show();
        }
        else if (password.isEmpty()){
            Toast.makeText(this,"Enter Password...", Toast.LENGTH_SHORT).show();
        }
        else if (confirmPassword.isEmpty()){
            Toast.makeText(this,"Enter Re-type password ....", Toast.LENGTH_SHORT).show();
        }
        else if (password != confirmPassword){
            Toast.makeText(this,"Password doesn't matches...", Toast.LENGTH_SHORT).show();
        }
        else{
            createUserAccount()
        }
    }

    private fun createUserAccount() {
        progressDialog.setMessage("Creating Account")
        progressDialog.show()
    }


}