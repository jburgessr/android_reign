package cl.jdomynyk.reign.presentation.presenters

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BasePresenter<View> : ViewModel(), CoroutineScope {

    private var view: View? = null
    private lateinit var context: Context

    private val job = SupervisorJob()
    final override val coroutineContext = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun attachView(view: View) {
        this.view = view
    }

    fun setContext(context: Context) {
        this.context = context
    }

    protected fun view(): View? {
        return view
    }

    protected fun context(): Context {
        return context
    }

    protected fun scope(): CoroutineScope {
        return scope
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        job.cancel()
        view = null
    }
}