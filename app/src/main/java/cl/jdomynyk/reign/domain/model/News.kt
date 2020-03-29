package cl.jdomynyk.reign.domain.model

class News(
    val objectID: Long,
    val storyTitle: String?,
    val storyText: String?,
    val title: String?,
    val author: String,
    val createAt: String,
    val storyURL: String?,
    val url: String?
) {
    override fun toString(): String {
        return "News(objectID=$objectID, storyTitle=$storyTitle, storyText=$storyText, title=$title, author=$author, create=$createAt, storyURL=$storyURL, url=$url)"
    }
}