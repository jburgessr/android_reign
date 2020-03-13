package cl.jdomynyk.reign.domain.interactor

import cl.jdomynyk.reign.domain.Handler


interface UseCase<T, P> {
    fun execute(handler: Handler<T>, params: P)
}