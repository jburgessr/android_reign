package cl.jdomynyk.reign.platform.di;

import cl.jdomynyk.reign.platform.di.list.ListActivityModule
import cl.jdomynyk.reign.platform.views.list.ListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(ListActivityModule::class)])
    @PerActivity
    internal abstract fun bindListActivity(): ListActivity

}