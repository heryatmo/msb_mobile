package com.example.heryatmo.msb_mobile.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.heryatmo.msb_mobile.R
import com.example.heryatmo.msb_mobile.model.Post
import kotlinx.android.synthetic.main.list_post.view.*
import java.util.ArrayList

class CustomAdapter(private val postList: ArrayList<Post>) :RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.list_post, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = postList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position],position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(post : Post, position: Int){
            itemView.txtViewName.text = post.nama
            itemView.txtViewPost.text = post.kebutuhan
        }
    }
}