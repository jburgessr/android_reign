package cl.jdomynyk.reign.presentation.presenters

import cl.jdomynyk.reign.R
import cl.jdomynyk.reign.domain.model.News
import cl.jdomynyk.reign.domain.Handler
import cl.jdomynyk.reign.domain.usecase.NewsUseCase
import cl.jdomynyk.reign.presentation.ListView
import cl.jdomynyk.reign.presentation.NewsCellView
import cl.jdomynyk.reign.presentation.formatters.Formatter
import kotlinx.coroutines.*

class ListPresenter(
    private val newsUseCase: NewsUseCase,
    private val formatter: Formatter,
    view: ListView
) : BasePresenter<ListView>(view) {
    private var list: MutableList<News> = emptyList<News>().toMutableList()

    fun viewReady() {
        scope().launch {
            loadLocalData()
        }
    }

    fun getRemoteNews() {
        newsUseCase.findRemote(object : Handler<List<News>> {
            override fun success(result: List<News>) {
                saveAllNews(result)
            }

            override fun error(exception: Exception) {
                view()?.let {
                    it.cancelRefreshDialog()
                    it.showSnackMessage(context().getString(R.string.msg_something_went_wrong))
                }
            }

        })
    }

    private fun saveAllNews(result: List<News>) {
        if (result.isNotEmpty()) {
            scope().launch {
                newsUseCase.saveAllNews(result)
                delay(3000)
                withContext(coroutineContext) {
                    showData(newsUseCase.getAll())
                }
            }
        } else {
            showError()
        }
    }

    private suspend fun loadLocalData() {
        scope().launch {
            val localList = newsUseCase.getAll().toMutableList()
            withContext(coroutineContext) {
                showData(localList)
            }
        }
    }

    private fun showData(result: List<News>) {
        view()?.cancelRefreshDialog()

        list = result.toMutableList()
        if (list.isNotEmpty()) {
            view()?.let {
                it.hideEmpty()
                it.showList()
                it.refreshList()
            }
        } else {
            showError()
        }
    }

    private fun showError() {
        view()?.let {
            it.hideList()
            it.showEmpty()
        }
    }

    /**
     * method for implement adapter and get items count
     */
    fun getItemsCount(): Int {
        return if (list.isEmpty()) 0 else list.size
    }

    /**
     * method for populate data on item
     */
    fun populateItem(cellView: NewsCellView, position: Int) {
        val item = list[position]
        val title = if (item.title.isNullOrEmpty()) item.storyTitle else item.title
        if (title != null) {
            cellView.setFields(title, formatter.formatDate(item.createAt), item.author)
        }
    }

    /**
     * method to capture the click event
     */
    fun onItemClick(position: Int) {
        val item = list[position]
        val url = item.url ?: item.storyURL
        if (url != null) {
            view()?.navigateToDetailScreen(url)
        } else {
            view()?.showSnackMessage(context().getString(R.string.msg_url_not_found))
        }
    }

    /**
     * method for remove item from de list
     */
    fun removeItem(position: Int) {
        scope().launch {
            newsUseCase.blockedNews(list[position].objectID)
            withContext(coroutineContext) {
                list.removeAt(position)
                view()?.refreshList()
            }
        }
    }
}