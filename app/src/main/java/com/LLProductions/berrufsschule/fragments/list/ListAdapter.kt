package com.LLProductions.berrufsschule.fragments.list

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.LLProductions.berrufsschule.R
import com.LLProductions.berrufsschule.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = userList[position]
        holder.itemView.tv_Number.text = currentItem.id.toString()
        holder.itemView.tv_FirstName.text = currentItem.firstName
        holder.itemView.tv_LastName.text = currentItem.lastName
        holder.itemView.tv_Age.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
       return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()

    }
}