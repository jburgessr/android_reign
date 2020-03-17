package cl.jdomynyk.reign.platform.di.app;

import android.app.Application
import cl.jdomynyk.reign.data.source.local.AppDatabase
import cl.jdomynyk.reign.platform.ReignApp
import cl.jdomynyk.reign.platform.di.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class],
    dependencies = [AppDatabase::class]
)
interface AppComponent : AndroidInjector<ReignApp> {

    override fun inject(app: ReignApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

        fun database(appDatabase: AppDatabase): Builder
    }

}