package com.assignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import assignment.app.R
import com.domain.model.UserDomain

class UserDetailAdapter(
    private val context: Context,
    private val userList: List<UserDomain>,
) : RecyclerView.Adapter<UserDetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_row_people, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userDetail = userList[position]
        holder.userName.text = userDetail.name
        holder.userEmail.text = userDetail.email
        holder.gender.text = userDetail.gender
        if (holder.gender.text == context.resources.getString(R.string.string_gender_male)){
            holder.gender.setBackgroundColor(ContextCompat.getColor(context,R.color.shade_blue))
        } else {
            holder.gender.setBackgroundColor(ContextCompat.getColor(context,R.color.dark_pink))
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.tv_user_name)
        var userEmail: TextView = itemView.findViewById(R.id.tv_user_email)
        var gender: TextView = itemView.findViewById(R.id.tv_user_gender)
    }
}