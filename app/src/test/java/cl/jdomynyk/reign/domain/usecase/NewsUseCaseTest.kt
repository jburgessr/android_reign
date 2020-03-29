package cl.jdomynyk.reign.domain.usecase

import cl.jdomynyk.reign.data.exception.ServiceThrowable
import cl.jdomynyk.reign.data.source.NewsRepositoryImpl
import cl.jdomynyk.reign.domain.RemoteResult
import cl.jdomynyk.reign.news

import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals

class NewsUseCaseTest {

    private lateinit var useCase: NewsUseCase
    private val mockRepo: NewsRepositoryImpl = mock()

    private val serviceThrowable: ServiceThrowable = mock()
    private val serviceTimeout: ServiceThrowable = mock()

    @Before
    fun setUp() {
        useCase = NewsUseCase(mockRepo)
    }

    @Test
    fun `get news remote success`() {
        runBlocking {
            whenever(mockRepo.getRemoteNews()).thenReturn(RemoteResult.Success(news))
            val result = useCase.findRemote()
            verify(mockRepo).getRemoteNews()
            assertEquals(result, RemoteResult.Success(news))
        }
    }

    @Test
    fun `get news remote failed`() {
        runBlocking {
            whenever(mockRepo.getRemoteNews()).thenReturn(RemoteResult.Error(404, "error occured"))
            val result = useCase.findRemote()
            verify(mockRepo).getRemoteNews()
            assertEquals(result, RemoteResult.Error(404, "error occured"))
        }
    }

    @Test
    fun `get news remote serviceThrowable`() {
        runBlocking {
            whenever(mockRepo.getRemoteNews()).thenReturn(RemoteResult.Exception(serviceThrowable))
            val result = useCase.findRemote()
            verify(mockRepo).getRemoteNews()
            assertEquals(result, RemoteResult.Exception(serviceThrowable))
        }
    }

    @Test
    fun `get news remote serviceTimeout`() {
        runBlocking {
            whenever(mockRepo.getRemoteNews()).thenReturn(RemoteResult.Exception(serviceTimeout))
            val result = useCase.findRemote()
            verify(mockRepo).getRemoteNews()
            assertEquals(result, RemoteResult.Exception(serviceTimeout))
        }
    }

    @Test
    fun `get news local`() {
        runBlocking {
            whenever(mockRepo.getLocalNews()).thenReturn(news)
            val result = useCase.getAll()
            verify(mockRepo).getLocalNews()
            assertEquals(
                result, news
            )
        }
    }

}