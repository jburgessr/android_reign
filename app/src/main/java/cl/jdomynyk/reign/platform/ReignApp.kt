package cl.jdomynyk.reign.platform

import androidx.room.Room
import cl.jdomynyk.reign.platform.di.app.DaggerAppComponent
import cl.jdomynyk.reign.data.source.local.AppDatabase
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class ReignApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<ReignApp> {
        val database: AppDatabase = Room.databaseBuilder(
            this, AppDatabase::class.java, "pulent_db.db"
        ).build()

        val component = DaggerAppComponent.builder().database(database).application(this).build()

        component.inject(this)

        return component
    }

}