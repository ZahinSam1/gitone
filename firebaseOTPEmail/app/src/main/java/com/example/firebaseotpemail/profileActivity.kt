package com.example.firebaseotpemail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp_varification.*

class profileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val phnNumber : String = intent.getStringExtra("phone").toString()

        tv_phonNumberShow.text = "+880-${phnNumber}"
    }
}