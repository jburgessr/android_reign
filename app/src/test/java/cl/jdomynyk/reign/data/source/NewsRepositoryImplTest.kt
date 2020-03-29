package cl.jdomynyk.reign.data.source

import cl.jdomynyk.reign.data.entities.mapper.DataEntityMapper
import cl.jdomynyk.reign.data.source.local.news.NewsLocalImpl
import cl.jdomynyk.reign.data.source.remote.NewsService
import cl.jdomynyk.reign.domain.RemoteResult
import cl.jdomynyk.reign.entities
import cl.jdomynyk.reign.news
import cl.jdomynyk.reign.remote

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.Before
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import retrofit2.Response

class NewsRepositoryImplTest {

    private lateinit var repo: NewsRepositoryImpl
    private val mockDataEntityMapper = DataEntityMapper()
    private val mockNewsService: NewsService = mock()
    private val mockNewLocal: NewsLocalImpl = mock()

    @Before
    fun setUp() {
        repo = NewsRepositoryImpl(mockDataEntityMapper, mockNewsService, mockNewLocal)
    }

    @Test
    fun `get news remote success`() {
        runBlocking {

            val response = Response.success(remote)
            val deferred = async { response }

            whenever(mockNewsService.getNews()).thenReturn(deferred)
            val result = repo.getRemoteNews()
            verify(mockNewsService).getNews()
            RemoteResult.Success(news) == result
        }
    }

    @Test
    fun `get news local`() {
        runBlocking {
            whenever(mockNewLocal.getAll()).thenReturn(entities)
            repo.getLocalNews()
            verify(mockNewLocal).getAll()
        }
    }

    @Test
    fun `save news local`() {
        runBlocking {
            repo.saveNews(news)
            verify(mockNewLocal).insertAllSync(mockDataEntityMapper.transformToEntity(news))
        }
    }

    @Test
    fun `hide news local`() {
        runBlocking {
            repo.hideNews(1)
            verify(mockNewLocal).hide(1)
        }
    }
}