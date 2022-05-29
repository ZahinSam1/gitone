package com.example.firebaseotpemail

import android.icu.util.ValueIterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_get_data.*

class GetData : AppCompatActivity() {

    private lateinit var databaseRef : DatabaseReference
    private lateinit var username : String
    private lateinit var user : user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        username = intent.getStringExtra("username").toString()

        getData()


    }

    private fun getData() {
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        if(userSnapshot.toString() == username){
                            user = userSnapshot.getValue(user::class.java)!!
                            
                            setData(user)
                            
                        }
                        else{
                            tv_fetchProfIntro.text = "Sorry no such name was found!!"
                            Toast.makeText(applicationContext, "Error finding name", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setData(user : user) {
        tv_fetchProfIntro.text = "Welcome ${user.userName}"
        tv_name.text = user.FirstName + user.LastName
        tv_age.text = user.Age
        tv_phoneNumber.text = user.phnNumber
    }
}