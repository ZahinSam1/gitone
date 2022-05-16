package com.example.firebaseotpemail

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_enter.*
import kotlinx.android.synthetic.main.activity_otp_varification.*
import java.util.concurrent.TimeUnit

class otpEnterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_enter)

        var auth = FirebaseAuth.getInstance()
        var phoneNumber = ""
        if(et_numberText.text.toString() != ""){
            phoneNumber = et_numberText.text.toString()

            btn_sendButton.setOnClickListener{
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // Activity (for callback binding)
                    //.setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)

                val intent = Intent(this, otpVarificationActivity::class.java)

                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
        }



    }
}