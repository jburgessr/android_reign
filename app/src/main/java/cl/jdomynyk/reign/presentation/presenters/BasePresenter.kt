package cl.jdomynyk.reign.presentation.presenters

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BasePresenter<View>(private var view: View) : ViewModel(), CoroutineScope {

    private lateinit var context: Context

    private val job = SupervisorJob()
    final override val coroutineContext = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

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
    }
}