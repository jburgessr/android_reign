package cl.jdomynyk.reign.platform.di.list;

import cl.jdomynyk.reign.domain.usecase.NewsUseCase
import cl.jdomynyk.reign.platform.views.list.ListActivity
import cl.jdomynyk.reign.presentation.formatters.Formatter
import cl.jdomynyk.reign.presentation.presenters.ListPresenter
import dagger.Module
import dagger.Provides


@Module
class ListActivityModule {

    @Provides
    internal fun providePresenter(
        activity: ListActivity, newsUseCase: NewsUseCase,
        formatter: Formatter
    ): ListPresenter {
        val presenter = ListPresenter(newsUseCase, formatter, activity)
        presenter.setContext(activity)
        return presenter
    }

}