package com.example.nbcamp_kakao

import android.app.DownloadManager.Query
import android.content.Context

object Util {
    fun saveSearch(context: Context, query: String){
        val pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
        val edit = pref.edit()
        edit.putString("name",query)
        edit.apply()
    }
    fun getSearch(context:Context): String{
        val pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE)
        if(pref == null){
            return ""
        }else {
            return pref.getString("name","").toString()
        }


    }
}