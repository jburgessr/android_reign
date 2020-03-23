package cl.jdomynyk.reign.platform.di.app;

import android.app.Application
import android.content.Context
import cl.jdomynyk.reign.data.entities.mapper.DataEntityMapper
import cl.jdomynyk.reign.data.source.NewsRepositoryImpl
import cl.jdomynyk.reign.data.source.local.news.NewsDao
import cl.jdomynyk.reign.data.source.local.news.NewsLocalImpl
import cl.jdomynyk.reign.data.source.remote.NewsRemoteImpl
import cl.jdomynyk.reign.data.source.remote.NewsService
import cl.jdomynyk.reign.domain.repository.NewsRepository
import cl.jdomynyk.reign.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun hitRepository(newsDao: NewsDao): NewsLocalImpl {
        return NewsLocalImpl(newsDao)
    }

    @Provides
    @Singleton
    internal fun provideService(): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRemoteDataSource(newsService: NewsService): NewsRemoteImpl {
        return NewsRemoteImpl(newsService)
    }

    @Provides
    @Singleton
    internal fun provideRepository(
        newsLocalImpl: NewsLocalImpl,
        newsRemoteImpl: NewsRemoteImpl,
        dataEntityMapper: DataEntityMapper
    ): NewsRepository {
        return NewsRepositoryImpl(dataEntityMapper, newsRemoteImpl, newsLocalImpl)
    }

    @Provides
    @Singleton
    internal fun newUseCase(repository: NewsRepository): NewsUseCase {
        return NewsUseCase(repository)
    }

}
