package com.example.myappli.screen.main.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myappli.R
import com.example.myappli.model.Category
import com.example.myappli.screen.category.CategoryAdapter

class FeatureAdapter(val context: Context) : RecyclerView.Adapter<FeatureAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(category: Category)
    }

    private var mListener: OnItemClickListener? = null

    fun setListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    private var mList: List<Category> = listOf()
    fun setNewList(list: List<Category>) {
        mList = list
        notifyDataSetChanged()
        Log.d("TAG", "setNewList: $mList")

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var cardTitle: TextView = itemView.findViewById(R.id.cardTitle)
        private var cardImage: ImageView = itemView.findViewById(R.id.cardImage)


        fun setDataBinding(category: Category, mListener: OnItemClickListener?, context: Context) {
            cardTitle.text = category.name
            when (category.name) {
                "Nature" -> {
                    cardImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_leaf
                        )
                    )
                }
                "Animals" -> {
                    cardImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_lion
                        )
                    )
                }
                "Food" -> {
                    cardImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_hamburger
                        )
                    )
                }
                "Fashion" -> {
                    cardImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_dress
                        )
                    )
                }
                "Celebrities" -> {
                    cardImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_party
                        )
                    )
                }
                "Travel/Active/Sports" -> {
                    cardTitle.text = "Travel"
                    cardImage.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_globe
                        )
                    )
                }
            }

            itemView.setOnClickListener {
                mListener?.onItemClick(category)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataBinding(mList[position], mListener, context)
    }

    override fun getItemCount(): Int {
        return if (mList.isNullOrEmpty()) 0 else mList.size
    }
}