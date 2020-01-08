package com.mohansaimanthri.greedyimageloader.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohansaimanthri.greedyimageengine.R
import com.mohansaimanthri.greedyimageloader.ui.MainActivity
import com.mohansaimanthri.greedyimageloader.utils.ImageLoader
import com.mohansaimanthri.greedyimageloader.utils.StringDiffUtils
import com.mohansaimanthri.greedyimageloader.utils.extensions.inflate

class MainAdapter(private val mInterface: MainActivity) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val listOfItems = arrayListOf<String>()

    interface MyInterface {
        fun clickedAtPos(position: Int, listOfItems: ArrayList<String>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.skeleton_main_recycler)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfItems.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ImageLoader.with(holder.imageView.context)
            .load(listOfItems[position],/*placeholder*/R.drawable.download, holder.imageView)
        ImageLoader.with(holder.profileImageView.context)
            .load(
                "https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=3ef46b07bb19f68322d027cb8f9ac99f",/*placeholder*/
                R.drawable.person,
                holder.profileImageView
            )

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView =
            itemView.findViewById<com.fmsirvent.ParallaxEverywhere.PEWImageView>(R.id.imageView)!!
        val profileImageView = itemView.findViewById<ImageView>(R.id.profile)!!

        init {
            imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v) {
                imageView -> {
                    mInterface.clickedAtPos(adapterPosition, listOfItems)
                }
            }
        }
    }

    fun receiveList(newList: ArrayList<String>) {
        val result =
            DiffUtil.calculateDiff(StringDiffUtils(listOfItems, newList))
        listOfItems.clear()
        listOfItems.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}