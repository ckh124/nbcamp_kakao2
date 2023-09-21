package com.example.nbcamp_kakao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nbcamp_kakao.bookmark.BookmarkFragment

import com.example.nbcamp_kakao.databinding.MainActivityBinding
import com.example.nbcamp_kakao.search.SearchFragment
import com.example.nbcamp_kakao.search.SearchItemModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val viewPagerAdapter by lazy{
        MainViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding){
        viewPager.adapter = viewPagerAdapter
        viewPager.offscreenPageLimit = viewPagerAdapter.itemCount
        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            tab.setText(viewPagerAdapter.getTitle(position))
        }.attach()


    }
    fun addBookmarkItem(item:SearchItemModel){
        val bookmarkFragment = viewPagerAdapter.getFragment(1) as BookmarkFragment
        bookmarkFragment.addBookmarkList(item)
    }

    fun removeBookmarkItem(item:SearchItemModel){
        val bookmarkFragment = viewPagerAdapter.getFragment(1) as BookmarkFragment
        bookmarkFragment.removeBookmarkList(item)
    }
    fun modifyIsLikeItem(item:SearchItemModel){
        val SearchFragment = viewPagerAdapter.getFragment(0) as SearchFragment
        SearchFragment.modifyIsLikeSearchItem(item)
    }
}