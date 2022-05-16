package com.example.firebaseotpemail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp_varification.*

class otpVarificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_varification)

        btn_Varify.setOnClickListener{
            startActivity(Intent(this, profileActivity::class.java))
        }
    }
}