package com.example.to_dolist

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.DataClass.MyAdapter
import com.example.to_dolist.Model.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dialogalert.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listiten.*

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var arrayList: ArrayList<User>? = null
    var dialog: Dialog? = null
    var database: FirebaseDatabase? = null
    var auth: FirebaseAuth? = null
    var dbrf: DatabaseReference? = null

    var reference: DatabaseReference? = null
    var titlee: TextInputEditText? = null
    var textt: TextInputEditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.setHasFixedSize(true)
        arrayList = arrayListOf()

        val tit = titlee?.text.toString()

        reference = FirebaseDatabase.getInstance().getReference("Users")
        dialog = Dialog(this)
        dialog!!.setContentView(R.layout.activity_dialogalert)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.window?.setBackgroundDrawable(getDrawable(R.drawable.newdialog))
        dialog!!.window?.setLayout(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        titlee = dialog!!.hading
        textt = dialog!!.notetodo

        dialog!!.setCancelable(false)
        noteadd.setOnClickListener { dialog!!.show() }
        dialog!!.back.setOnClickListener { dialog!!.dismiss() }
        dialog!!.save.setOnClickListener { senddata() }
        showdata()
        mytodo.setOnClickListener { startActivity(Intent(this, AboutDeveloper::class.java)) }
    }


    private fun showdata() {
        dbrf = FirebaseDatabase.getInstance().getReference("Users")
        dbrf!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snaphot1 in snapshot.children) {
                        if (snaphot1.exists()) {
                            val user = snaphot1.getValue(User::class.java)
                            arrayList?.add(user!!)

                        }
                        val adapter = MyAdapter(arrayList)
                        recyclerView!!.adapter = adapter
                        adapter.setOnitemclick(object : MyAdapter.onitemclic {
                            override fun OnitemClick(position: Int) {

                                val intent = Intent(this@MainActivity, DataActivity::class.java)
                                intent.putExtra("data", arrayList!![position].Text)
                                intent.putExtra("title", arrayList!![position].Title)

                                startActivity(intent)

                            }

                        })

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun senddata() {

        val tit = titlee!!.text.toString()
        val txt = textt!!.text.toString()
        val user = User(tit, txt)
        reference!!.child(tit).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                dialog!!.dismiss()
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Saved failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}