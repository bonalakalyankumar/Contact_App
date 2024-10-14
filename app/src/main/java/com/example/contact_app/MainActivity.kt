package com.example.contact_app

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var butn:FloatingActionButton


    private lateinit var edittext1: EditText
    private lateinit var edittext2: EditText
    private lateinit var ImagePrev: ImageView
    private lateinit var butnPicture: Button
    private lateinit var btn_save: Button
    private lateinit var dialog: Dialog

    private lateinit var contactadapter: Contactadapter
    private val listofcontacts = mutableListOf<contacttype>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        butn= findViewById(R.id.btn)
        rv = findViewById(R.id.rv)

        // Initialize RecyclerView and Adapter
        rv.layoutManager = LinearLayoutManager(this)
        contactadapter = Contactadapter(listofcontacts)
        rv.adapter = contactadapter

        // Floating Action Button Click Listener
        butn.setOnClickListener {
            showInputDialog()
        }
    }

    private fun showInputDialog() {
        dialog=Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.datafed_2)

        edittext1 = dialog.findViewById(R.id.edt_name)
        edittext2 = dialog.findViewById(R.id.edt_name1)
        ImagePrev = dialog.findViewById(R.id.image_prev)
        butnPicture = dialog.findViewById(R.id.btn_picture)
        btn_save = dialog.findViewById(R.id.savepic)



        // Picture Button Click Listener
        butnPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 101)
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {

            ImagePrev.visibility = View.VISIBLE
            ImagePrev.setImageURI(data?.data) // Display the selected image

            btn_save.setOnClickListener {
                val edtname = edittext1.text.toString()
                val edtnumber = edittext2.text.toString()
                val ImagePrev =data?.data


                val contact = contacttype(
                    Heading = edtname,
                    SubHeading = edtnumber,
                    imageRes = ImagePrev!! // Store the selected image URI
                )

                listofcontacts.add(contact)
                contactadapter.notifyDataSetChanged()
                dialog.dismiss() // Close the dialog


            }
        }
    }
}
