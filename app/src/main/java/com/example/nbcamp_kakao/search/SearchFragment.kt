package com.example.nbcamp_kakao.search

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nbcamp_kakao.MainActivity
import com.example.nbcamp_kakao.Util
import com.example.nbcamp_kakao.api.Constants
import com.example.nbcamp_kakao.api.RetrofitInstance.api
import com.example.nbcamp_kakao.databinding.SearchFragmentBinding
import com.example.nbcamp_kakao.model.ImageSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.format.DateTimeFormatter

class SearchFragment:Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val list: ArrayList<SearchItemModel> = ArrayList()
    private lateinit var mContext:Context
    private lateinit var gridmanager: StaggeredGridLayoutManager
    private lateinit var adapter: SearchAdapter



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
    }


    companion object {
        fun newInstance() = SearchFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        initView()
        searchBtnClicked()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun initView() {

        gridmanager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.searchRecycler.layoutManager = gridmanager

       adapter = SearchAdapter(mContext,
           onClickLike = {item ->
               (activity as MainActivity)?.addBookmarkItem(item)

       },
           onClickDisLike = {item ->
               (activity as MainActivity)?.removeBookmarkItem(item)

           }
       )
        binding.searchRecycler.adapter = adapter
        binding.searchRecycler.itemAnimator = null

        var lastSearch =Util.getSearch(requireContext())
        binding.searchEdittext.setText(lastSearch)

    }
    private fun searchBtnClicked(){
        binding.searchBtn.setOnClickListener{
            val query = binding.searchEdittext.text.toString()
            if(query.isNotEmpty()){
                Util.saveSearch(mContext,query)
                adapter.clearItem()
                Log.d("test1","help!")
                fetchImageResults(query)
            }
            val closeKeyboard =requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager //키보드 숨기기 기능 구현을 위한 변수
            closeKeyboard.hideSoftInputFromWindow(binding.searchEdittext.windowToken,0)
        }

    }
    private fun fetchImageResults(query: String) {
        Log.d("query","$query")
        api.searchImage(Constants.AUTH_HEADER, query, "sort", 1, 80)?.enqueue(object :
            Callback<ImageSearchResponse> {
            override fun onResponse(
                call: Call<ImageSearchResponse>,
                response: Response<ImageSearchResponse>
            ) {
                response.body()?.metaData?.let { meta ->
                    if(meta.totalCount!! > 0){
                        response.body()!!.documents?.forEach { document ->
                            val title = document.sitename
                            val url = document.image_url
                            val convertdate = document.datetime
                            val date = convertdate.format(DateTimeFormatter.ofPattern("yyyy.mm.dd HH:mm:ss"))

                            list.add(SearchItemModel(title, date, url))
                            Log.d("test2","$title,$date, $url")
                    }

                    }

                }
                Log.d("test4","$list")
                adapter.items = list
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ImageSearchResponse>, t: Throwable) {
                Log.d("failApi","...")
            }


        })
    }
    fun modifyIsLikeSearchItem(item:SearchItemModel){
        fun findIndex(item:SearchItemModel): Int?{
            val findSearchModelByUrl = list?.find{
                it.url == item.url
            }
            return list?.indexOf(findSearchModelByUrl)
        }
        list[findIndex(item)!!].isLike = false
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}