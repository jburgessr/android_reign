package cl.jdomynyk.reign.platform.di.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import cl.jdomynyk.reign.data.entities.mapper.DataEntityMapper
import cl.jdomynyk.reign.data.source.NewsRepositoryImpl
import cl.jdomynyk.reign.data.source.local.AppDatabase
import cl.jdomynyk.reign.data.source.local.news.NewsDao
import cl.jdomynyk.reign.data.source.local.news.NewsLocalImpl
import cl.jdomynyk.reign.data.source.remote.NewsService
import cl.jdomynyk.reign.domain.repository.NewsRepository
import cl.jdomynyk.reign.domain.usecase.NewsUseCase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
    internal fun provideDB(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "pulent_db.db"
        ).build()
    }

    @Provides
    @Singleton
    internal fun provideNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.getNewsDao()
    }

    @Provides
    @Singleton
    internal fun provideNewsLocalImpl(newsDao: NewsDao): NewsLocalImpl {
        return NewsLocalImpl(newsDao)
    }

    @Provides
    @Singleton
    internal fun provideService(): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(NewsService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRepository(
        newsLocalImpl: NewsLocalImpl,
        newsService: NewsService,
        dataEntityMapper: DataEntityMapper
    ): NewsRepository {
        return NewsRepositoryImpl(dataEntityMapper, newsService, newsLocalImpl)
    }

    @Provides
    @Singleton
    internal fun newUseCase(repository: NewsRepository): NewsUseCase {
        return NewsUseCase(repository)
    }

}
