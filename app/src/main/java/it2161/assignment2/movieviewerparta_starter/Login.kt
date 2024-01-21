package it2161.assignment2.movieviewerparta_starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.etPassword

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Register button
        btnRegister.setOnClickListener(View.OnClickListener {
            val intentToRegister = Intent(this,Register::class.java)
            startActivity(intentToRegister)
        })

        // Login button
        btnLogin.setOnClickListener(View.OnClickListener {
            // Handle empty field
            var hasErrors = false

            if (etName.text.toString().isEmpty()) {
                etName.error = "Field cannot be empty"
                hasErrors = true
            }

            if (etPassword.text.toString().isEmpty()) {
                etPassword.error = "Field cannot be empty"
                hasErrors = true
            }

            if (hasErrors) {
                // Handle errors as needed, e.g., show a toast or return early
                return@OnClickListener
            }

            // Since no errors, navigate to SimpleViewListOfMoviesActivity (SVLOMA)
            val intentToSVLOMA = Intent(this,SimpleViewListOfMoviesActivity::class.java)
            startActivity(intentToSVLOMA)

        })
    }

}