package cl.jdomynyk.reign.platform.views.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.jdomynyk.reign.R
import cl.jdomynyk.reign.presentation.NewsCellView
import cl.jdomynyk.reign.presentation.presenters.ListPresenter
import kotlinx.android.synthetic.main.activity_list_item.view.*


class NewsAdapter(private val presenter: ListPresenter) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewsHolder {
        if (viewGroup is RecyclerView) {
            val view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_list_item, viewGroup, false)
            return NewsHolder(view)
        } else {
            throw RuntimeException("Not bound")
        }
    }

    override fun onBindViewHolder(hitsHolder: NewsHolder, position: Int) {
        presenter.populateItem(hitsHolder, position)
    }

    override fun getItemCount(): Int {
        return presenter.getItemsCount()
    }

    fun removeAt(position: Int) {
        presenter.removeItem(position)
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        NewsCellView {

        init {
            itemView.cvTrack.setOnClickListener { presenter.onItemClick(adapterPosition) }
        }

        override fun setFields(title: String, date: String, author: String) {
            itemView.tvTitle.text = title
            itemView.tvDate.text = date
            itemView.tvAuthor.text = author
        }
    }
}