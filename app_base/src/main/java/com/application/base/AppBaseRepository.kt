package com.application.base

// T => Template types
abstract class AppBaseRepository<T : Any> {
    lateinit var api: T
}