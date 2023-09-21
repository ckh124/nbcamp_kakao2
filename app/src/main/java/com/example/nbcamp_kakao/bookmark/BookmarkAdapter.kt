package com.example.nbcamp_kakao.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbcamp_kakao.databinding.BookmarkFragmentBinding
import com.example.nbcamp_kakao.databinding.BookmarkItemBinding

import com.example.nbcamp_kakao.search.SearchItemModel

class BookmarkAdapter(context: Context,
                      private val onClickBookmark: (SearchItemModel) -> Unit
): RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    var items = ArrayList<SearchItemModel>()
    var mContext = context

    fun clearItem(){
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class ViewHolder(
        private val binding: BookmarkItemBinding

    ):RecyclerView.ViewHolder(binding.root){


        fun bind(item: SearchItemModel) = with(binding){
            bookmarkTitle.text = item.title
            bookmarkText.text = item.dataTime
            container.setOnClickListener{
                    onClickBookmark(
                        item
                    )
            }


            Glide.with(mContext)
                .load(item.url)
                .into(binding.bookmarkImg)

        }


    }
}









