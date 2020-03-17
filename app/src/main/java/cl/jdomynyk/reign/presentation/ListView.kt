package cl.jdomynyk.reign.presentation

interface ListView : BaseView {

    fun showEmpty()

    fun hideEmpty()

    fun showList()

    fun hideList()

    fun refreshList()

    fun cancelRefreshDialog()

    fun navigateToDetailScreen(url: String)
}