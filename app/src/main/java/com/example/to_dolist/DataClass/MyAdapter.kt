package com.example.to_dolist.DataClass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.Model.User
import com.example.to_dolist.R
import kotlin.coroutines.coroutineContext

class MyAdapter(private val userList: ArrayList<User>?,
                ):RecyclerView.Adapter<MyAdapter.MyviewHolder>(){
    private lateinit var mListner:onitemclic
interface onitemclic{
    fun OnitemClick(position: Int)
}
    fun setOnitemclick(clicklister:onitemclic){
        mListner=clicklister

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
val itemview=LayoutInflater.from(parent.context).inflate(R.layout.listiten,parent,false)
        return MyviewHolder(itemview,mListner)

    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
val currentuser= userList?.get(position)

        holder.top.text=currentuser!!.Title
        holder.botom.text= currentuser.Text

    }

    override fun getItemCount(): Int {
        return userList!!.size
    }
   inner class MyviewHolder (itemview:View,onitemclick: onitemclic):RecyclerView.ViewHolder(itemview){
        val top:TextView=itemview.findViewById(R.id.mainhading)
        val botom:TextView=itemview.findViewById(R.id.detail)

init {
    itemview.setOnClickListener {
        onitemclick.OnitemClick(adapterPosition)

    }
}




    }

}