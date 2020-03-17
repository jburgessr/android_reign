package cl.jdomynyk.reign.data.source.remote.response

import cl.jdomynyk.reign.data.entities.NewsEntity
import com.google.gson.annotations.SerializedName

class NewsReponse(
    @SerializedName("hits") val list: List<NewsEntity>
)