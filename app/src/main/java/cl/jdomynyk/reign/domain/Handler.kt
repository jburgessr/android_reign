package cl.jdomynyk.reign.domain

interface Handler<T> {

    fun result(result: T)

    fun error(exception: Exception)

}