package cl.jdomynyk.reign

import cl.jdomynyk.reign.data.entities.NewsEntity
import cl.jdomynyk.reign.data.exception.ServiceThrowable
import cl.jdomynyk.reign.data.source.remote.response.NewsReponse
import cl.jdomynyk.reign.domain.model.News
import com.nhaarman.mockitokotlin2.mock


val serviceThrowable: ServiceThrowable = mock()
val serviceTimeout: ServiceThrowable = mock()

val remote = NewsReponse(
    listOf(
        NewsEntity(
            1,
            "Title",
            "Story Text",
            null,
            "test",
            "2020-03-28T20:20:26.000Z",
            null,
            "https://www.google.cl"
        )
    )
)

val entities = listOf(
    NewsEntity(
        1,
        "Title",
        "Story Text",
        null,
        "test",
        "2020-03-28T20:20:26.000Z",
        null,
        "https://www.google.cl"
    )
)

val news = listOf(
    News(
        1,
        "Title",
        "Story Text",
        null,
        "test",
        "2020-03-28T20:20:26.000Z",
        null,
        "https://www.google.cl"
    )
)