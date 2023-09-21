package com.example.nbcamp_kakao.bookmark

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nbcamp_kakao.MainActivity
import com.example.nbcamp_kakao.Util
import com.example.nbcamp_kakao.databinding.BookmarkFragmentBinding
import com.example.nbcamp_kakao.search.SearchAdapter
import com.example.nbcamp_kakao.search.SearchItemModel

class BookmarkFragment: Fragment() {
    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding!!
    private val list: ArrayList<SearchItemModel> = ArrayList()
    private lateinit var mContext:Context
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var adapter: BookmarkAdapter

    companion object{
        fun newInstance() = BookmarkFragment()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkFragmentBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initView() {

        gridmanager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.bookmarkRecycler.layoutManager = gridmanager

        adapter = BookmarkAdapter(mContext,
            onClickBookmark = {item ->
                (activity as MainActivity)?.modifyIsLikeItem(item)
                removeBookmarkList(item)
        })
        binding.bookmarkRecycler.adapter = adapter
        binding.bookmarkRecycler.itemAnimator = null



    }
    fun addBookmarkList(item:SearchItemModel){
        list.add(item)
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
    fun removeBookmarkList(item:SearchItemModel){
        list.remove(item)
        adapter.items = list
        adapter.notifyDataSetChanged()
    }

}