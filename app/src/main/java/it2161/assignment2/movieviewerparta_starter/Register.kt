package it2161.assignment2.movieviewerparta_starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
//            val name = etName.text.toString().trim()
//            val password = etPassword.text.toString().trim()
//            val email = etEmail.text.toString().trim()
//            val adminNumber = etAdminNumber.text.toString().trim()
//            val pemGrp = etPemGrp.text.toString().trim()

            var hasErrors = false

            if (etName.text.toString().isEmpty()) {
                etName.error = "Field cannot be empty"
                hasErrors = true
            }

            if (etPassword.text.toString().isEmpty()) {
                etPassword.error = "Field cannot be empty"
                hasErrors = true
            }

            if (etEmail.text.toString().isEmpty()) {
                etEmail.error = "Field cannot be empty"
                hasErrors = true
            }

            if (etAdminNumber.text.toString().isEmpty()) {
                etAdminNumber.error = "Field cannot be empty"
                hasErrors = true
            }

            if (etPemGrp.text.toString().isEmpty()) {
                etPemGrp.error = "Field cannot be empty"
                hasErrors = true
            }

            if (hasErrors) {
                // Handle errors as needed, e.g., show a toast or return early
                return@setOnClickListener
            }

            // Intent to verification
            val intentToVerification = Intent(this,Verification::class.java)
            startActivity(intentToVerification)

        }
    }
}