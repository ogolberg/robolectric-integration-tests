package com.example.testapp

interface Repository {
    fun <T> get(key: String, type: Class<T>): T
}