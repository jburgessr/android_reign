package cl.jdomynyk.reign.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @SerializedName("objectID") val objectID: Long,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("story_text") val storyText: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("author") val author: String,
    @SerializedName("created_at") val createAt: String,
    @SerializedName("story_url") val storyURL: String?,
    @SerializedName("url") val url: String?,
    val blocked: Int = 0
) {
    override fun toString(): String {
        return "NewsEntity(objectID=$objectID, storyTitle=$storyTitle, storyText=$storyText, title=$title, author='$author', createAt='$createAt', storyURL=$storyURL, url=$url, blocked=$blocked)"
    }
}