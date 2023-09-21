package com.example.nbcamp_kakao.model


import com.google.gson.annotations.SerializedName
import java.time.format.DateTimeFormatter
import java.util.Date

data class ImageSearchResponse(
    @SerializedName("meta")
    val metaData: MetaData?,
    @SerializedName("documents")
    val documents: ArrayList<Documents>
){
    data class Documents(
        @SerializedName("display_sitename")
        val sitename : String,
        val collection : String,
        val image_url : String,
        val datetime: String
    )
}
data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("is_end")
    val isEnd:Boolean?
)
