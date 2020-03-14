package cl.jdomynyk.reign.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hits")
data class HitsEntity(
    @PrimaryKey
    @SerializedName("story_id") val storyID: Long,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("story_text") val storyText: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val create: String?
)