package com.example.nbcamp_kakao.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbcamp_kakao.databinding.SearchItemBinding


class SearchAdapter(context: Context,
                    private val onClickLike:(SearchItemModel) ->Unit,
                    private val onClickDisLike:(SearchItemModel) ->Unit,
): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var items = ArrayList<SearchItemModel>()
    var mContext = context

    fun clearItem(){
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.bind(item)
    }


    inner class ViewHolder(
        private val binding: SearchItemBinding

        ):RecyclerView.ViewHolder(binding.root){


            fun bind(item: SearchItemModel) = with(binding){
                searchTitle.text = item.title
                searchText.text = item.dataTime
                if(item.isLike == false){
                    likeImg.visibility = View.INVISIBLE
                }else{
                    likeImg.visibility = View.VISIBLE
                }




                container.setOnClickListener{
                    if(!item.isLike){
                        item.isLike = true
                        likeImg.visibility = View.VISIBLE
                        onClickLike(
                            item
                        )
                    }else{
                        item.isLike=false
                        likeImg.visibility = View.INVISIBLE
                        onClickDisLike(
                            item
                        )
                    }

                }


                Glide.with(mContext)
                    .load(item.url)
                    .into(binding.searchImg)

            }


    }




}