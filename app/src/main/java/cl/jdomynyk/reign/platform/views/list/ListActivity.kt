package cl.jdomynyk.reign.platform.views.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.jdomynyk.reign.R
import cl.jdomynyk.reign.platform.navigation.gotToDetail
import cl.jdomynyk.reign.platform.views.list.adapter.NewsAdapter
import cl.jdomynyk.reign.presentation.ListView
import cl.jdomynyk.reign.presentation.presenters.ListPresenter
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ListActivity : DaggerAppCompatActivity(), ListView {

    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var presenter: ListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setUpRecycleView()

        setUpRefreshView()

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

    private fun informPresenterViewIsReady() {
        presenter.viewReady()
    }

    override fun showEmpty() {
        llError.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        llError.visibility = View.GONE
    }

    override fun showList() {
        recyclerView.visibility = View.VISIBLE
    }

    override fun hideList() {
        recyclerView.visibility = View.GONE
    }

    override fun refreshList() {
        adapter.notifyDataSetChanged()
    }

    override fun cancelRefreshDialog() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun navigateToDetailScreen(url: String) {
        gotToDetail(url)
    }

    override fun showSnackMessage(message: String) {
        Snackbar.make(relativeLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
