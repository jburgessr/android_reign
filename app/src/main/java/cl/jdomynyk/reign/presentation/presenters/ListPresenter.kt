package cl.jdomynyk.reign.presentation.presenters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.jdomynyk.reign.R
import cl.jdomynyk.reign.data.exception.ServiceThrowable
import cl.jdomynyk.reign.domain.RemoteResult
import cl.jdomynyk.reign.domain.model.News
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
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> get() = _news

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun viewReady() {
        _isLoading.value = true
        scope().launch {
            loadLocalData()
        }
    }

    fun getRemoteNews() {
        _isLoading.value = true
        scope().launch {
            val result = newsUseCase.findRemote()
            _isLoading.postValue(false)
            when (result) {
                is RemoteResult.Success -> saveAllNews(result.items)
                is RemoteResult.Error -> _error.postValue(ServiceThrowable())
                is RemoteResult.Exception -> _error.postValue(result.exception)
            }
        }
    }

    private fun saveAllNews(result: List<News>) {
        scope().launch {
            newsUseCase.saveAllNews(result)
            _news.postValue(newsUseCase.getAll())
        }
    }

    private suspend fun loadLocalData() {
        _isLoading.postValue(false)
        scope().launch {
            _news.postValue(newsUseCase.getAll())
            _news.notifyObserver()
        }
    }

    /**
     * method for implement adapter and get items count
     */
    fun getItemsCount(): Int {

        return if (_news.value != null) {
            _news.value!!.size
        } else {
            0
        }
    }

    /**
     * method for populate data on item
     */
    fun populateItem(cellView: NewsCellView, position: Int) {
        val item = _news.value?.get(position)
        if (item != null) {
            val title = if (item.title.isNullOrEmpty()) item.storyTitle else item.title
            if (title != null) {
                cellView.setFields(title, formatter.formatDate(item.createAt), item.author)
            }
        }
    }

    /**
     * method to capture the click event
     */
    fun onItemClick(position: Int) {
        val item = _news.value?.get(position)
        if (item != null) {
            val url = item.url ?: item.storyURL
            if (url != null) {
                view()?.navigateToDetailScreen(url)
            } else {
                view()?.showMessageUrlNotFound()
            }
        }
    }

    /**
     * method for remove item from de list
     */
    fun removeItem(position: Int) {
        scope().launch {
            _news.value?.get(position)?.objectID?.let { newsUseCase.blockedNews(it) }
            (_news.value as MutableList).removeAt(position)
            _news.notifyObserver()
        }
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}