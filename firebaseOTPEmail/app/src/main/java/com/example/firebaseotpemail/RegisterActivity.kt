package com.example.firebaseotpemail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_reg_register.setOnClickListener {

            val firstName = et_1stName.text.toString()
            val lastName = et_lastName.text.toString()
            val age = et_age.text.toString()
            val phnNum = et_phnNumber.text.toString()
            username = et_UserName.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Users")

            val userInfo = user(firstName, lastName, age, phnNum, username)
            database.child(username).setValue(userInfo).addOnSuccessListener{

                et_1stName.text.clear()
                et_lastName.text.clear()
                et_age.text.clear()
                et_phnNumber.text.clear()
                et_UserName.text.clear()

                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this, "Error! Data was not saved!", Toast.LENGTH_SHORT).show()
            }


        }

        btn_fetchData.setOnClickListener {
            val intent = Intent(this, GetData::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}