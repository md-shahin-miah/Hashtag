package com.example.myappli.screen.main.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.R
import com.example.myappli.model.Category

class SearchAdapter(private val  context: Context) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(category: Category)
    }

    private  var mListener: OnItemClickListener? = null

    fun setListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    private var mList: List<Category> = listOf()
    fun setNewList(list: List<Category>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataBinding(mList[position], mListener, position, context)
    }

    override fun getItemCount(): Int {
        Log.d("TAG", "getItemCount: ${mList.size}")
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.item_title)
        private var rootLayout: ConstraintLayout = itemView.findViewById(R.id.rootLayout)


        fun setDataBinding(
            category: Category,
            mListener: OnItemClickListener?,
            position: Int,
            context: Context
        ) {


//            if (position % 2 == 0) {
//                rootLayout.setBackgroundColor(
//                    ContextCompat.getColor(
//                        context,
//                        R.color.pap_light
//                    )
//                )
//            }
//


            title.text = category.name

            itemView.setOnClickListener {
                mListener?.onItemClicked(category)
            }

        }
    }
}