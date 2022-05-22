package com.example.firebaseotpemail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_enter.*
import kotlinx.android.synthetic.main.activity_otp_varification.*
import org.jetbrains.annotations.NotNull

class otpVarificationActivity : AppCompatActivity() {
    private var verificationID : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_varification)

        tv_phonNumberShow.text = "+880-${intent.getStringExtra("phoneNumber")}"
        val phnNumber : String = intent.getStringExtra("phoneNumber").toString()
        verificationID = intent.getStringExtra("verificationId").toString()

        //var otp = verificationID.replace("<#> ", "").split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]


        btn_Varify.visibility = View.VISIBLE



        btn_Varify.setOnClickListener{

            if(verificationID != null){

                var enteredOTP = et_otpText.text.toString()
                var credential = PhoneAuthProvider.getCredential(verificationID, enteredOTP)

                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(this) {
                    task ->
                    if(task.isSuccessful){
                        val user = task.result?.user
                        val intent = Intent(this, profileActivity::class.java)
                        intent.putExtra("phone", phnNumber)
                        
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "sign in with credential failed", Toast.LENGTH_SHORT).show()
                        if(task.exception is FirebaseAuthInvalidCredentialsException){
                            Toast.makeText(this, "credential exception error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
        
    }
}

/*
OnCompleteListener<AuthResult>() {
                        fun onComplete(task : Task<AuthResult>){
                            if(task.isSuccessful){
                                var intent = Intent(this, profileActivity::class.java)
                                intent.putExtra("phone", phnNumber)
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                                startActivity(intent)

                            }
                            else{
                                Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }

*/