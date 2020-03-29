package cl.jdomynyk.reign.platform.views.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.jdomynyk.reign.R
import cl.jdomynyk.reign.data.exception.ServiceTimeOut
import cl.jdomynyk.reign.platform.navigation.gotToDetail
import cl.jdomynyk.reign.platform.views.list.adapter.NewsAdapter
import cl.jdomynyk.reign.presentation.ListView
import cl.jdomynyk.reign.presentation.presenters.ListPresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ListActivity : DaggerAppCompatActivity(), ListView {

    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var presenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setUpRecycleView()

        setUpRefreshView()

        initObserverList()

        initObserverProgress()

        initObserverError()

        informPresenterViewIsReady()
    }

    private fun setUpRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(presenter)
        recyclerView.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as NewsAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setUpRefreshView() {
        swipeRefreshLayout.setOnRefreshListener { presenter.getRemoteNews() }
    }

    private fun initObserverList() {
        presenter.news.observe(this, Observer { list ->
            refreshList()
            if (list != null && list.isNotEmpty()) {
                showList()
                hideEmpty()
            } else {
                hideList()
                showEmpty()
            }
        })
    }

    private fun initObserverProgress() {
        presenter.isLoading.observe(this, Observer { isProgress ->
            swipeRefreshLayout.isRefreshing = isProgress
        })
    }

    private fun initObserverError() {
        presenter.error.observe(this, Observer { exception ->
            if (exception is ServiceTimeOut) {
                showSnackMessage(getString(R.string.msg_timeout))
            } else {
                showSnackMessage(getString(R.string.msg_something_went_wrong))
            }
        })
    }

    private fun informPresenterViewIsReady() {
        presenter.viewReady()
    }

    private fun showEmpty() {
        llError.visibility = View.VISIBLE
    }

    private fun hideEmpty() {
        llError.visibility = View.GONE
    }

    private fun showList() {
        recyclerView.visibility = View.VISIBLE
    }

    private fun hideList() {
        recyclerView.visibility = View.GONE
    }

    private fun refreshList() {
        adapter.notifyDataSetChanged()
    }

    private fun showSnackMessage(message: String) {
        Snackbar.make(relativeLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showMessageUrlNotFound() {
        Snackbar.make(relativeLayout, getString(R.string.msg_url_not_found), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun navigateToDetailScreen(url: String) {
        gotToDetail(url)
    }
}
