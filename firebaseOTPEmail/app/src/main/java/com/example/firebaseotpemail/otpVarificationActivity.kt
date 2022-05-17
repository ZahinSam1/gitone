package com.example.firebaseotpemail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_otp_enter.*
import kotlinx.android.synthetic.main.activity_otp_varification.*

class otpVarificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val verificationID : String

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_varification)

        tv_phonNumberShow.text = String.format("+880-%s", intent.getStringExtra("phone"))
        val phnNumber : String = intent.getStringExtra("phone").toString()
        verificationID = intent.getStringExtra("verificationID").toString()

        var enteredOTP = et_otpText.text.toString()
        btn_Varify.visibility = View.INVISIBLE
        if(enteredOTP == verificationID){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, profileActivity::class.java)
            intent.putExtra("phone", phnNumber)
            startActivity(intent)
        }
    }
}