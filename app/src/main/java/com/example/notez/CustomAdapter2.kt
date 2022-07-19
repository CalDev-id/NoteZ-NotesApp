package com.example.notez

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.template_item.view.*
import kotlinx.android.synthetic.main.template_recycle_atas.view.*

class CustomAdapter2(private var array_adp2: ArrayList<DataClass2>) : RecyclerView.Adapter<CustomAdapter2.CustomViewHolder2>() {

    //ini item click
    private lateinit var clicked2: itemClick

    interface itemClick {
        fun click(position: Int)
    }

    fun setItemClick(isClicked:itemClick){
        clicked2 = isClicked
    }


    class CustomViewHolder2(parent: View, itemClick: itemClick) : RecyclerView.ViewHolder(parent) {
        val judulHold2: TextView? = itemView.waktu
        val catatanHold2: TextView? = itemView.todolist

        //ini item click
        init {
            itemView.setOnClickListener {
                itemClick.click(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder2 {
        val buatHolder2 = LayoutInflater.from(parent.context).inflate(R.layout.template_recycle_atas, parent, false)
        return CustomViewHolder2(buatHolder2, clicked2)
    }

    override fun onBindViewHolder(holder: CustomViewHolder2, position: Int) {
        holder.judulHold2?.text = array_adp2[position].data_waktu
        holder.catatanHold2?.text = array_adp2[position].data_todo
    }

    override fun getItemCount(): Int {return array_adp2.size}

    //search
    fun filteredData(filteredList: ArrayList<DataClass2>){
        array_adp2 = filteredList
        notifyDataSetChanged()
    }

}
