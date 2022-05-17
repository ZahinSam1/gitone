package com.example.firebaseotpemail

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.trimmedLength
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_enter.*
import kotlinx.android.synthetic.main.activity_otp_varification.*
import java.util.concurrent.TimeUnit

class otpEnterActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private var phoneNumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_enter)





            btn_sendButton.setOnClickListener{

                phoneNumber = et_numberText.text.toString()
                if(phoneNumber.isEmpty()){
                    Toast.makeText(this, "No input in number field", Toast.LENGTH_SHORT).show()
                }
                else if(phoneNumber.length != 10){
                    Toast.makeText(this, "Invalid Phone number", Toast.LENGTH_SHORT).show()
                }
                else{
                    //btn_sendButton.visibility = View.INVISIBLE
                    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        override fun  onVerificationCompleted(credential: PhoneAuthCredential) {

                        }
                        override fun  onVerificationFailed(e: FirebaseException) {
                            btn_sendButton.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, e.localizedMessage.toString(), Toast.LENGTH_LONG).show()
                        }
                        override fun  onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                            btn_sendButton.visibility = View.VISIBLE
                            val intent = Intent(applicationContext, otpVarificationActivity::class.java)
                            intent.putExtra("phoneNumber", et_numberText.text.toString())
                            intent.putExtra("verificationId", verificationId)
                            startActivity(intent)
                        }
                    }

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+880"+ phoneNumber.trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                }

            }



    }
}