package com.karan.apiintegration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recycler_Adapter(
    var array: ArrayList<ResponseModel.Data>,
    private var recyclerBtn: Recycler_btn
) : RecyclerView.Adapter<recycler_Adapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val email: TextView = view.findViewById(R.id.etEmail)
        val Fname: TextView = view.findViewById(R.id.etFirst_name)
        val Lname: TextView = view.findViewById(R.id.etLast_name)
        val btn_del: Button = view.findViewById(R.id.btn_Delete)
        val btn_update: Button = view.findViewById(R.id.btn_update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_list,
            parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = array[position]
        holder.email.setText(currentItem.emailRM)
        holder.Fname.setText(currentItem.first_name)
        holder.Lname.setText(currentItem.last_name)


        holder.btn_del.setOnClickListener {
            recyclerBtn.btn_del(position)
        }
        holder.btn_update.setOnClickListener {
            recyclerBtn.btn_update(position)
        }

    }
}