package cl.jdomynyk.reign.platform

import cl.jdomynyk.reign.platform.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ReignApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<ReignApp> {
        val component = DaggerAppComponent.builder().application(this).build()

        component.inject(this)

        return component
    }

}