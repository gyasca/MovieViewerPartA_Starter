package it2161.assignment2.movieviewerparta_starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_verification.*

class Verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnVerify.setOnClickListener(View.OnClickListener {
            // Handle empty field
            var hasErrors = false

            if (etVerificationCode.text.toString().isEmpty()) {
                etVerificationCode.error = "Field cannot be empty"
                hasErrors = true
            }

            if (hasErrors) {
                // Handle errors as needed, e.g., show a toast or return early
                return@OnClickListener
            }


            // Handle checking of verification code
            val correctCode = "1234"

            if(etVerificationCode.text.toString() != correctCode){
                displayToast("Code error")
            } else {
                displayToast("Code verified")

                // Since verification code is correct, navigate to login screen
                val intentToLogin = Intent(this,Login::class.java)
                startActivity(intentToLogin)
            }
        })
    }

    fun displayToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}