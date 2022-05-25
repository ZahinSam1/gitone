package com.example.firebaseotpemail

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_image_upload.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ImageUpload : AppCompatActivity() {
    public lateinit var imageUri : Uri
    private lateinit var storage : FirebaseStorage
    private lateinit var storageReference : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)

        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        btn_uploadImage.setOnClickListener{
            choosePictutes()
        }
    }

    private fun choosePictutes() {
        var intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK && data!= null && data.data != null){
            imageUri = data.data!!

            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri) //this converts the Uri to an image.
                iv_uploadedPic.setImageBitmap(bitmap)
                //imageTrue = 1
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //iv_uploadedPic.setImageURI(imageUri)

            uploadPicture();
            

        }
    }

    private fun uploadPicture() {
        /*var randomKey : String = UUID.randomUUID().toString()

        val mountainsRef = storageReference.child("image/${randomKey}")

// Create a reference to 'images/mountains.jpg'
        val mountainImagesRef = storageReference.child("images/mountains.jpg")

// While the file names are the same, the references point to different files
        mountainsRef.name == mountainImagesRef.name // true
        mountainsRef.path == mountainImagesRef.path // false

        mountainImagesRef.putFile(imageUri)
            .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot>()) {

            }*/

        val pd = ProgressDialog(this)
        pd.setMessage("Uploading Image...")
        pd.setCancelable(false)
        pd.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)

        val storageRef = FirebaseStorage.getInstance().getReference("Image/${fileName}")

        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                iv_uploadedPic.setImageURI(null)
                Toast.makeText(this, "Successfully Uploaded", Toast.LENGTH_SHORT).show()
                if(pd.isShowing) pd.dismiss()
            }
            .addOnFailureListener{

                if(pd.isShowing) pd.dismiss()

                Toast.makeText(this, "Failed to Upload", Toast.LENGTH_SHORT).show()

            }


    }
}

