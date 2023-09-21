package com.example.nbcamp_kakao

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nbcamp_kakao.bookmark.BookmarkFragment
import com.example.nbcamp_kakao.search.SearchFragment

class MainViewPagerAdapter(
    fragmentActivity:FragmentActivity
):FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()

    init{
        fragments.add(
            MainTabs(SearchFragment.newInstance(), R.string.search_tab)
        )
        fragments.add(
            MainTabs(BookmarkFragment.newInstance(), R.string.bookmark_tab)
        )

    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
    fun getTitle(position: Int): Int{
        return fragments[position].titleRes
    }
    fun getFragment(position:Int):Fragment{
        return fragments[position].fragment
    }
}