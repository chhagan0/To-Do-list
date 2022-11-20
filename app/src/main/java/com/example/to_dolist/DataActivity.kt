package com.example.to_dolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.to_dolist.Model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : AppCompatActivity() {
    private lateinit var dbref:DatabaseReference
    lateinit var dlt:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        val had=findViewById<TextView>(R.id.userhading)

newdataupdate.setOnClickListener{
    val hdd=had.text.toString()
    val newdatas=updatedata.text.toString()
    val uptit=updatetitle.text.toString()
    val map= mapOf(
        "text" to  newdatas,
        "title" to uptit


    )
    dbref=FirebaseDatabase.getInstance().getReference("Users")
    dbref.child(hdd).updateChildren(map).addOnCompleteListener { task->
        if (task.isSuccessful){
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
            updatedata.visibility=View.GONE
            textdata.visibility=View.VISIBLE
            newdataupdate.visibility=View.GONE
            updatetitle.visibility=View.GONE
            userhading.visibility=View.VISIBLE
        }
        else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }


}

edittext.setOnClickListener { editdata() }

        backbtn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        had.text=intent.getStringExtra("title")
        textdata.text=intent.getStringExtra("data")

deletebtn.setOnClickListener {
    val hdd=had.text.toString()
    dbref=FirebaseDatabase.getInstance().getReference("Users")
    dbref.child(hdd).removeValue()
    startActivity(Intent(this,MainActivity::class.java))
    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
}
    }



    private fun editdata() {
        val edit=findViewById<TextView>(R.id.textdata)
        val updatetit=userhading.text.toString()
        updatetitle.setText(updatetit)
        val data=edit.text.toString()
        updatedata.visibility=View.VISIBLE
        textdata.visibility=View.GONE
        newdataupdate.visibility=View.VISIBLE
        updatetitle.visibility=View.VISIBLE
        userhading.visibility=View.GONE
        updatedata.setText(data)

    }
}