package com.example.firebaseotpemail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_image_upload.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_usingPhone.setOnClickListener{
            startActivity(Intent(this, otpEnterActivity::class.java))
        }

        btn_upImg.setOnClickListener({
            startActivity(Intent(this, ImageUpload::class.java))
        })

    }
}