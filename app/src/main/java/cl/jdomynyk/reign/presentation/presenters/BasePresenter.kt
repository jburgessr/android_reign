package cl.jdomynyk.reign.presentation.presenters

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BasePresenter<View>(private var view: View) :
    ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    final override val coroutineContext = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    protected fun view(): View? {
        return view
    }

    protected fun scope(): CoroutineScope {
        return scope
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        job.cancel()
    }
}