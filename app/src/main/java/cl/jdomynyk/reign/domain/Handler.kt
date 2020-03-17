package cl.jdomynyk.reign.domain

interface Handler<T> {

     fun success(result: T)

     fun error(exception: Exception)

}