package com.application.base

// T => Template types
abstract class AppBaseRepository<T : Any> {

    abstract fun setApiClass(): T

    var api: T = this.setApiClass()

}